import org.checkerframework.checker.index.qual.*;

class HierarchyIndexFor {
	
	int[] arr = new int[5];
	int[] arrB = new int[5];
	
	@Unknown int i;
	
	void assignIndexFor(@IndexFor("arr") int v) { 
		i = v;
	}
	
	void assignIndexOrHigh(@IndexOrHigh("arr") int v) { 
		i = v;
	}
	
	void assignNonNegative(@NonNegative int v) { 
		i = v;
	}

	void assignIndexOrLow(@IndexOrLow("arr") int v) { 
		i = v;
	}
	
	void assignLTLength(@LTLength("arr") int v) { 
		i = v;
	}

	void assignUnknown(@Unknown int v) { 
		i = v;
	}

	void assignIndexForB(@IndexFor("arrB") int v) { 
		i = v;
	}

	void assignIndexOrHighB(@IndexOrHigh("arrB") int v) { 
		i = v;
	}

	void assignIndexOrLowB(@IndexOrLow("arrB") int v) { 
		i = v;
	}
	
	void assignLTLengthB(@LTLength("arrB") int v) { 
		i = v;
	}
}