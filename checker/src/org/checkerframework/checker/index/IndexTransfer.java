package org.checkerframework.checker.index;

import javax.lang.model.element.AnnotationMirror;

import org.checkerframework.checker.nonneg.NonNegAnalysis;
import org.checkerframework.dataflow.analysis.RegularTransferResult;
import org.checkerframework.dataflow.analysis.TransferInput;
import org.checkerframework.dataflow.analysis.TransferResult;
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
}
