package org.checkerframework.checker.index;

import javax.lang.model.element.AnnotationMirror;

import org.checkerframework.checker.index.qual.*;
import org.checkerframework.dataflow.analysis.ConditionalTransferResult;
import org.checkerframework.dataflow.analysis.FlowExpressions;
import org.checkerframework.dataflow.analysis.FlowExpressions.LocalVariable;
import org.checkerframework.dataflow.analysis.FlowExpressions.Receiver;
import org.checkerframework.dataflow.analysis.RegularTransferResult;
import org.checkerframework.dataflow.analysis.TransferInput;
import org.checkerframework.dataflow.analysis.TransferResult;
import org.checkerframework.dataflow.cfg.node.FieldAccessNode;
import org.checkerframework.dataflow.cfg.node.GreaterThanNode;
import org.checkerframework.dataflow.cfg.node.LocalVariableNode;
import org.checkerframework.dataflow.cfg.node.Node;
import org.checkerframework.framework.flow.CFAbstractTransfer;
import org.checkerframework.framework.flow.CFStore;
import org.checkerframework.framework.flow.CFValue;
import org.checkerframework.framework.type.AnnotatedTypeMirror;
import org.checkerframework.javacutil.AnnotationUtils;


public class IndexTransfer extends CFAbstractTransfer<CFValue, CFStore, IndexTransfer> {
	protected IndexAnalysis analysis;
	protected IndexAnnotatedTypeFactory atypeFactory;

	public IndexTransfer(IndexAnalysis analysis) {
		super(analysis);
		this.analysis = analysis;
		atypeFactory = (IndexAnnotatedTypeFactory) analysis.getTypeFactory();
	}

	// annotate arr.length to be IndexOrHigh("arr")
	@Override
	public TransferResult<CFValue, CFStore> visitFieldAccess(FieldAccessNode node, TransferInput<CFValue, CFStore> in) {
		TransferResult<CFValue, CFStore> result = super.visitFieldAccess(node, in);

		if(node.getFieldName().equals("length")){
			AnnotationMirror anno = atypeFactory.createIndexOrHighAnnotation(node.getReceiver().toString());
			CFValue newResultValue = analysis.createSingleAnnotationValue(anno, result.getResultValue().getType().getUnderlyingType());
			return new RegularTransferResult<>(newResultValue, result.getRegularStore());
		}

		return result;
	}

	@Override
	public TransferResult<CFValue, CFStore> visitGreaterThan(GreaterThanNode node, TransferInput<CFValue, CFStore> in){
		TransferResult<CFValue, CFStore> result = super.visitGreaterThan(node, in);
		Node left = node.getLeftOperand();
		Node right = node.getRightOperand();
		Receiver rec = FlowExpressions.internalReprOf(analysis.getTypeFactory(), left);
		AnnotatedTypeMirror leftType = atypeFactory.getAnnotatedType(left.getTree());
		
		CFStore thenStore = result.getRegularStore();
		CFStore elseStore = thenStore.copy();
		ConditionalTransferResult<CFValue, CFStore> newResult = 
				new ConditionalTransferResult<>(result.getResultValue(), thenStore, elseStore);
		
		if(leftType.hasAnnotation(Unknown.class)){
			UnknownGreaterThan(rec,right, thenStore);
		}
		if(leftType.hasAnnotation(IndexOrLow.class)){
			//return IndexOrLowGreaterThan(rec, right, thenStore);
		}


		return newResult;
	}

	//********************************************************************************//
	// these are methods for GreaterThan Nodes once left operand Annotation is known  //
	//********************************************************************************//
	// this returns a transfer result for @Unknown > x
	private void UnknownGreaterThan(Receiver rec, Node right, CFStore thenStore){
		AnnotatedTypeMirror rightType = atypeFactory.getAnnotatedType(right.getTree());
		// booleans to see if the type is any in the heirarchy we want to refine
		boolean IOL = rightType.hasAnnotation(IndexOrLow.class);
		boolean NN = rightType.hasAnnotation(NonNegative.class);
		boolean IOH = rightType.hasAnnotation(IndexOrHigh.class);
		boolean IF = rightType.hasAnnotation(IndexFor.class);
		if(IOL || NN || IOH || IF){
			AnnotationMirror anno = atypeFactory.createNonNegAnnotation();
			thenStore.insertValue(rec, anno);
		}
	}
	
//	private TransferResult<CFValue, CFStore> IndexOrLowGreaterThan(Receiver rec, Node right,
//			TransferResult<CFValue, CFStore> result) {
//		CFStore thenStore = result.getRegularStore();
//		CFStore elseStore = thenStore.copy();
//		ConditionalTransferResult<CFValue, CFStore> newResult = 
//				new ConditionalTransferResult<>(result.getResultValue(), thenStore, elseStore);
//		AnnotatedTypeMirror rightType = atypeFactory.getAnnotatedType(right.getTree());
//		for(AnnotationMirror anno: rightType.getAnnotations()){
//			boolean IOL = AnnotationUtils.areSameIgnoringValues(anno, atypeFactory.IndexOrLow);
//			boolean InF = AnnotationUtils.areSameIgnoringValues(anno, atypeFactory.IndexFor);
//			if(IOL || InF){
//				String aValue = IndexVisitor.getIndexValue(anno, atypeFactory.getValueMethod(anno));
//			}
//		}
//	}

}

