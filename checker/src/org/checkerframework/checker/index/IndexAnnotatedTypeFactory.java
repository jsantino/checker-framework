package org.checkerframework.checker.index;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.VariableElement;

import org.checkerframework.checker.index.qual.*;
import org.checkerframework.common.basetype.BaseTypeChecker;
import org.checkerframework.framework.flow.CFStore;
import org.checkerframework.framework.flow.CFValue;
import org.checkerframework.framework.type.AnnotatedTypeFactory;
import org.checkerframework.framework.type.AnnotatedTypeMirror;
import org.checkerframework.framework.type.GenericAnnotatedTypeFactory;
import org.checkerframework.framework.type.QualifierHierarchy;
import org.checkerframework.framework.type.treeannotator.ImplicitsTreeAnnotator;
import org.checkerframework.framework.type.treeannotator.ListTreeAnnotator;
import org.checkerframework.framework.type.treeannotator.PropagationTreeAnnotator;
import org.checkerframework.framework.type.treeannotator.TreeAnnotator;
import org.checkerframework.framework.util.AnnotationBuilder;
import org.checkerframework.framework.util.GraphQualifierHierarchy;
import org.checkerframework.framework.util.MultiGraphQualifierHierarchy.MultiGraphFactory;
import org.checkerframework.javacutil.AnnotationUtils;
import org.checkerframework.javacutil.Pair;

import com.sun.source.tree.LiteralTree;
import com.sun.source.tree.MemberReferenceTree;
import com.sun.source.tree.MemberSelectTree;
import com.sun.source.tree.Tree;

public class IndexAnnotatedTypeFactory
extends GenericAnnotatedTypeFactory<CFValue, CFStore, IndexTransfer, IndexAnalysis> {

	protected final AnnotationMirror IndexFor;
	protected final AnnotationMirror IndexBottom;
	protected final AnnotationMirror IndexOrLow;
	protected final AnnotationMirror IndexOrHigh;

	public IndexAnnotatedTypeFactory(BaseTypeChecker checker) {
		super(checker);
		IndexFor = AnnotationUtils.fromClass(elements, IndexFor.class);
		IndexBottom = AnnotationUtils.fromClass(elements, IndexBottom.class);
		IndexOrLow = AnnotationUtils.fromClass(elements, IndexOrLow.class);
		IndexOrHigh = AnnotationUtils.fromClass(elements, IndexOrHigh.class);
		this.postInit();
	}

	@Override
	public TreeAnnotator createTreeAnnotator() {
		return new ListTreeAnnotator(
				new ImplicitsTreeAnnotator(this),
				new IndexTreeAnnotator(this),
				new PropagationTreeAnnotator(this)
				);
	}

	@Override
	protected IndexAnalysis createFlowAnalysis(List<Pair<VariableElement, CFValue>> fieldvalues){
		return new IndexAnalysis(checker, this, fieldvalues);
	}

	private class IndexTreeAnnotator extends TreeAnnotator {
		public IndexTreeAnnotator(AnnotatedTypeFactory atypeFactory) {
			super(atypeFactory);
		}
		
		public Void visitLiteral(LiteralTree tree, AnnotatedTypeMirror type){
			if (!type.isAnnotatedInHierarchy(AnnotationUtils.fromClass(elements, NonNegative.class))) {
				if (tree.getKind() == Tree.Kind.INT_LITERAL) {
					if ((int)tree.getValue() > -1) {
						type.addAnnotation(createNonNegAnnotation());
					}
					if((int)tree.getValue() == -1){
						type.addAnnotation(createIndexorLowAnnotation(""));
					}
				}
			}
			return super.visitLiteral(tree, type);
		}
		
		// didn't work for intro a.length, doing it in transfer
/*		public Void visitMemberSelect(MemberSelectTree tree, AnnotatedTypeMirror type){
			String name = tree.getExpression().toString();
			String iden = tree.getIdentifier().toString();
			if(iden.equals("length")){
				System.out.println(tree.toString());
				type.addAnnotation(createIndexOrHighAnnotation(name));
			}
			return super.visitMemberSelect(tree, type);
		}*/

		
		
	}
    @Override
    protected Set<Class<? extends Annotation>> createSupportedTypeQualifiers() {
        return getBundledTypeQualifiersWithPolyAll(IndexFor.class);
    }

	@Override
	public QualifierHierarchy createQualifierHierarchy(MultiGraphFactory factory) {
		return new IndexQualifierHierarchy(factory, IndexBottom);
	}

	//********************************************************************************//
	// This is the class that handles the subtyping for our qualifiers                //
	//********************************************************************************//	
	private final class IndexQualifierHierarchy extends GraphQualifierHierarchy {

		public IndexQualifierHierarchy(MultiGraphFactory f, AnnotationMirror bottom) {
			super(f, bottom);
		}

		//values don't matter for our subtyping so use defaults when comparing.
				//  is there a better way to do this?
		@Override
		public boolean isSubtype(AnnotationMirror rhs, AnnotationMirror lhs) {
			// Ignore annotation values to ensure that annotation is in supertype map.
			if (AnnotationUtils.areSameIgnoringValues(lhs, IndexFor)) {
				lhs = IndexFor;
			}
			if (AnnotationUtils.areSameIgnoringValues(rhs, IndexFor)) {
				rhs = IndexFor;
			}
			if (AnnotationUtils.areSameIgnoringValues(lhs, IndexOrLow)) {
				lhs = IndexOrLow;
			}
			if (AnnotationUtils.areSameIgnoringValues(rhs, IndexOrLow)) {
				rhs = IndexOrLow;
			}
			if (AnnotationUtils.areSameIgnoringValues(lhs, IndexOrHigh)) {
				lhs = IndexOrHigh;
			}
			if (AnnotationUtils.areSameIgnoringValues(rhs, IndexOrHigh)) {
				rhs = IndexOrHigh;
			}
			return super.isSubtype(rhs, lhs);
		}
	}
	
	//********************************************************************************//
	// These are the methods that build the annotations we apply using this factory   //
	//********************************************************************************//	
	
	//returns a new @NonNegative annotation
	AnnotationMirror createNonNegAnnotation() {
		AnnotationBuilder builder = new AnnotationBuilder(processingEnv, NonNegative.class);
		return builder.build();
	}
	//returns a new @Unknown annotation
	AnnotationMirror createUnknownAnnotation(){
		AnnotationBuilder builder = new AnnotationBuilder(processingEnv, Unknown.class);
		return builder.build();
	}
	
	//returns a new @IndexOrLow annotation
	AnnotationMirror createIndexorLowAnnotation(String name) {
		AnnotationBuilder builder = new AnnotationBuilder(processingEnv, IndexOrLow.class);
		builder.setValue("value", name);
		return builder.build();
	}
	
	//returns a new @IndexOrHigh annotation
	AnnotationMirror createIndexOrHighAnnotation(String name) {
		AnnotationBuilder builder = new AnnotationBuilder(processingEnv, IndexOrHigh.class);
		builder.setValue("value", name);
		return builder.build();
	}
	

}
