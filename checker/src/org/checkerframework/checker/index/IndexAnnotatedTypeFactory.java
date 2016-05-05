package org.checkerframework.checker.index;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.ExecutableElement;
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
import org.checkerframework.javacutil.TreeUtils;

import com.sun.source.tree.BinaryTree;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.LiteralTree;
import com.sun.source.tree.MemberReferenceTree;
import com.sun.source.tree.MemberSelectTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.UnaryTree;

public class IndexAnnotatedTypeFactory
extends GenericAnnotatedTypeFactory<CFValue, CFStore, IndexTransfer, IndexAnalysis> {

	// base annotations
	protected final AnnotationMirror IndexFor;
	protected final AnnotationMirror IndexBottom;
	protected final AnnotationMirror IndexOrLow;
	protected final AnnotationMirror IndexOrHigh;
	protected final AnnotationMirror LTLength;
	protected final AnnotationMirror NonNegative;
	protected final AnnotationMirror Unknown;

	// methods to get values
	protected final ExecutableElement IndexForValueElement;
	protected final ProcessingEnvironment env;

	public IndexAnnotatedTypeFactory(BaseTypeChecker checker) {
		super(checker);
		IndexFor = AnnotationUtils.fromClass(elements, IndexFor.class);
		IndexBottom = AnnotationUtils.fromClass(elements, IndexBottom.class);
		IndexOrLow = AnnotationUtils.fromClass(elements, IndexOrLow.class);
		IndexOrHigh = AnnotationUtils.fromClass(elements, IndexOrHigh.class);
		LTLength = AnnotationUtils.fromClass(elements, LTLength.class);
		NonNegative = AnnotationUtils.fromClass(elements, NonNegative.class);
		Unknown = AnnotationUtils.fromClass(elements, Unknown.class);

		env = checker.getProcessingEnvironment();
		IndexForValueElement = TreeUtils.getMethod("org.checkerframework.checker.index.qual.IndexFor", "value", 0, env);
		this.postInit();
	}

	// uses the qualhierarchy to share subtype information
	public boolean isSubtype(AnnotationMirror rhs, AnnotationMirror lhs) {
		return qualHierarchy.isSubtype((rhs), (lhs));
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
					int val = (int) tree.getValue();
					if (val > 0) {
						type.addAnnotation(createNonNegAnnotation());
					}
					else if(val == -1){
						type.addAnnotation(createIndexOrLowAnnotation(""));
					}
					else if(val == 0){
						type.addAnnotation(createIndexOrHighAnnotation(""));
					}
				}
			}
			return super.visitLiteral(tree, type);
		}
		//*****************************************************************//
		// these are the method that handle Binary operations (+- etc.)    //
		//*****************************************************************//
		public Void visitBinary(BinaryTree tree, AnnotatedTypeMirror type){
			switch (tree.getKind()){
			// call both directions for commutativity
			case PLUS:
				ExpressionTree left = tree.getLeftOperand();
				ExpressionTree right = tree.getRightOperand();
				visitPlus(left, right, type);
				//visitPlus(right, left, type);
				break;
			case MINUS:
				visitMinus(tree, type);
				break;
			default:
				break;
			}
			return super.visitBinary(tree, type);
		}

		// do addition between types
		public void visitPlus(ExpressionTree leftExpr, ExpressionTree rightExpr, AnnotatedTypeMirror type){
			AnnotatedTypeMirror left = getAnnotatedType(leftExpr);
			AnnotatedTypeMirror right = getAnnotatedType(rightExpr);
			// if left is literal 1 swap sides because we can handle that.
			if(leftExpr.getKind()== Tree.Kind.INT_LITERAL && (int)((LiteralTree)leftExpr).getValue() == 1){
				visitPlus(rightExpr, leftExpr, type);
				return;
			}

			for(AnnotationMirror anno: left.getAnnotations()){
				// if the right side is a literal we do some special stuff(specifically for 1)
				if(rightExpr.getKind() == Tree.Kind.INT_LITERAL && (int)((LiteralTree)rightExpr).getValue() == 1){
					if(qualHierarchy.isSubtype(anno, IndexOrLow)){
						type.removeAnnotation(anno);
						String value = IndexVisitor.getIndexValue(anno, getValueMethod(anno));
						type.addAnnotation(createIndexOrHighAnnotation(value));						
					}
					else if(qualHierarchy.isSubtype(anno, NonNegative)){
						type.removeAnnotation(anno);
						type.addAnnotation(createNonNegAnnotation());
					}
					else{
						type.removeAnnotation(anno);
					}
				}
				// anything a subtype of nonneg + subtype nonneg = nonnegative
				else if(right.hasAnnotationRelaxed(IndexFor) || right.hasAnnotationRelaxed(IndexOrHigh) || right.hasAnnotation(NonNegative)){
					type.clearAnnotations();
					if(qualHierarchy.isSubtype(anno, NonNegative)){				
						type.addAnnotation(createNonNegAnnotation());						
					}
					else{
						type.addAnnotation(createUnknownAnnotation());
					}
				}
				else{
					type.clearAnnotations();
					type.addAnnotation(createUnknownAnnotation());
				}
			}
		}
		
		// make increments and decrements work properly
		@Override
		public Void visitUnary(UnaryTree tree,  AnnotatedTypeMirror type){
			switch(tree.getKind()){
			case PREFIX_INCREMENT:
				preInc(tree, type);
				break;
			case POSTFIX_INCREMENT:
				break;
			default:
				break;
			}
			return super.visitUnary(tree, type);
		}
		
		private void preInc(UnaryTree tree, AnnotatedTypeMirror type) {
			AnnotatedTypeMirror  ATM = getAnnotatedType(tree.getExpression());
			for(AnnotationMirror anno: ATM.getAnnotations()){
				if(qualHierarchy.isSubtype(anno, IndexOrLow)){
					type.removeAnnotation(anno);
					String value = IndexVisitor.getIndexValue(anno, getValueMethod(anno));
					type.addAnnotation(createIndexOrHighAnnotation(value));						
				}
				else if(qualHierarchy.isSubtype(anno, NonNegative)){
					type.removeAnnotation(anno);
					type.addAnnotation(createNonNegAnnotation());
				}
				else{
					type.removeAnnotation(anno);
				}
			}
		}

		public void visitMinus(BinaryTree tree, AnnotatedTypeMirror type){

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
			return super.isSubtype(refine(rhs), refine(lhs));
		}

		// get the type annotation without the value
		private AnnotationMirror refine(AnnotationMirror type){
			if (AnnotationUtils.areSameIgnoringValues(type, LTLength)) {
				return LTLength;
			}
			else if (AnnotationUtils.areSameIgnoringValues(type, IndexFor)) {
				return IndexFor;
			}
			else if (AnnotationUtils.areSameIgnoringValues(type, IndexOrHigh)) {
				return IndexOrHigh;
			}
			else if (AnnotationUtils.areSameIgnoringValues(type, IndexOrLow)) {
				return IndexOrLow;
			}
			return type;
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
	AnnotationMirror createIndexOrLowAnnotation(String name) {
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

	//returns a new @LTLength annotation
	AnnotationMirror createLTLengthAnnotation(String name) {
		AnnotationBuilder builder = new AnnotationBuilder(processingEnv, LTLength.class);
		builder.setValue("value", name);
		return builder.build();
	}

	//returns a new @IndexFor annotation
	AnnotationMirror createIndexForAnnotation(String name) {
		AnnotationBuilder builder = new AnnotationBuilder(processingEnv, IndexFor.class);
		builder.setValue("value", name);
		return builder.build();
	}

	// returns the value method specific to the class of the anno passed in
	protected ExecutableElement getValueMethod(AnnotationMirror anno) {
		if(AnnotationUtils.areSameIgnoringValues(anno, IndexFor)){
			return TreeUtils.getMethod("org.checkerframework.checker.index.qual.IndexFor", "value", 0, env);
		}
		if(AnnotationUtils.areSameIgnoringValues(anno, IndexOrLow)){
			return TreeUtils.getMethod("org.checkerframework.checker.index.qual.IndexOrLow", "value", 0, env);
		}
		if(AnnotationUtils.areSameIgnoringValues(anno, IndexOrHigh)){
			return TreeUtils.getMethod("org.checkerframework.checker.index.qual.IndexOrHigh", "value", 0, env);
		}
		if(AnnotationUtils.areSameIgnoringValues(anno, LTLength)){
			return TreeUtils.getMethod("org.checkerframework.checker.index.qual.LTLength", "value", 0, env);
		}
		return null;
	}


}
