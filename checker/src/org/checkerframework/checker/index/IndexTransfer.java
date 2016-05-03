package org.checkerframework.checker.index;

import javax.lang.model.element.AnnotationMirror;

import org.checkerframework.checker.nonneg.NonNegAnalysis;
import org.checkerframework.checker.nonneg.NonNegAnnotatedTypeFactory;
import org.checkerframework.dataflow.analysis.RegularTransferResult;
import org.checkerframework.dataflow.analysis.TransferInput;
import org.checkerframework.dataflow.analysis.TransferResult;
import org.checkerframework.dataflow.cfg.node.FieldAccessNode;
import org.checkerframework.dataflow.cfg.node.GreaterThanOrEqualNode;
import org.checkerframework.dataflow.cfg.node.NumericalSubtractionNode;
import org.checkerframework.framework.flow.CFAbstractTransfer;
import org.checkerframework.framework.flow.CFStore;
import org.checkerframework.framework.flow.CFValue;


public class IndexTransfer extends CFAbstractTransfer<CFValue, CFStore, IndexTransfer> {
	protected IndexAnalysis analysis;
	
	public IndexTransfer(IndexAnalysis analysis) {
		super(analysis);
		this.analysis = analysis;
	}
	
	// annotate arr.length to be IndexOrHigh("arr")
	@Override
	public TransferResult<CFValue, CFStore> visitFieldAccess(FieldAccessNode node, TransferInput<CFValue, CFStore> in) {

		IndexAnnotatedTypeFactory atypeFactory = (IndexAnnotatedTypeFactory) analysis.getTypeFactory();
		TransferResult<CFValue, CFStore> result = super.visitFieldAccess(node, in);
		
		if(node.getFieldName().equals("length")){
			AnnotationMirror anno = atypeFactory.createIndexOrHighAnnotation(node.getReceiver().toString());
			CFValue newResultValue = analysis.createSingleAnnotationValue(anno, result.getResultValue().getType().getUnderlyingType());
			return new RegularTransferResult<>(newResultValue, result.getRegularStore());
		}

		return result;
	}
	}

