import org.checkerframework.checker.index.qual.IndexFor;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.IndexOrLow;
import org.checkerframework.checker.index.qual.LTLength;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.Unknown;

class TransferSubIndexOrHigh {

	
	int[] arr = new int[5];
	int[] arrB = new int[5];
	
	// This error can be suppressed
	//:: error: (assignment.type.incompatible)
	@IndexOrHigh("arr") int i = 2;
	
	void subIndexFor(@IndexFor("arr") int v) {
		// Show result is of type Unknown
		@Unknown int nn1 = v + i;
		//:: error (assignment.type.incompatible)
		@NonNegative int nn = v + i;
		//:: error (assignment.type.incompatible)
		@LTLength("arr") int lTLength = v + i;
	}

	void subIndexOrLow(@IndexOrLow("arr") int v) {
		// Show result is of type Unknown
		@Unknown int nn1 = v + i;
		//:: error (assignment.type.incompatible)
		@NonNegative int nn = v + i;
		//:: error (assignment.type.incompatible)
		@LTLength("arr") int lTLength = v + i;
	}
	
	void subIndexOrHigh(@IndexOrHigh("arr") int v) {
		// Show result is of type Unknown
		@Unknown int nn1 = v + i;
		//:: error (assignment.type.incompatible)
		@NonNegative int nn = v + i;
		//:: error (assignment.type.incompatible)
		@LTLength("arr") int lTLength = v + i;
	}
	
	void sub1(@IndexOrLow("arr") int indexOrLow1, @IndexOrLow("arr") int indexOrLow2) {
		
		int result = i - 1;
		
		// Show result is of type IndexOrLow
		indexOrLow1 = result;
		result = indexOrLow2;
	}
	
	void sub0(@IndexOrHigh("arr") int indexOrHigh1, @IndexOrHigh("arr") int indexOrHigh2) {

		int result = i - 0;
		
		// Show result is of type IndexOrHigh
		indexOrHigh1 = result;
		result = indexOrHigh2;
	}
	
	void subLTLength(@LTLength("arr") int v) {
		// Show result is of type Unknown
		@Unknown int nn1 = v + i;
		//:: error (assignment.type.incompatible)
		@NonNegative int nn = v + i;
		//:: error (assignment.type.incompatible)
		@LTLength("arr") int lTLength = v + i;
	}
	
	void subNonNegative(@NonNegative int v) {
		// Show result is of type Unknown
		@Unknown int nn1 = v + i;
		//:: error (assignment.type.incompatible)
		@NonNegative int nn = v + i;
		//:: error (assignment.type.incompatible)
		@LTLength("arr") int lTLength = v + i;
	}

	void subUnknown(@Unknown int v) {
		// Show result is of type Unknown
		@Unknown int nn1 = v + i;
		//:: error (assignment.type.incompatible)
		@NonNegative int nn = v + i;
		//:: error (assignment.type.incompatible)
		@LTLength("arr") int lTLength = v + i;
	}

	void subIndexForB(@IndexFor("arrB") int v) {
		// Show result is of type Unknown
		@Unknown int nn1 = v + i;
		//:: error (assignment.type.incompatible)
		@NonNegative int nn = v + i;
		//:: error (assignment.type.incompatible)
		@LTLength("arr") int lTLength = v + i;
	}
	
	void subIndexOrLowB(@IndexOrLow("arrB") int v) {
		// Show result is of type Unknown
		@Unknown int nn1 = v + i;
		//:: error (assignment.type.incompatible)
		@NonNegative int nn = v + i;
		//:: error (assignment.type.incompatible)
		@LTLength("arr") int lTLength = v + i;
	}

	void subIndexOrHighB(@IndexOrHigh("arrB") int v) {
		// Show result is of type Unknown
		@Unknown int nn1 = v + i;
		//:: error (assignment.type.incompatible)
		@NonNegative int nn = v + i;
		//:: error (assignment.type.incompatible)
		@LTLength("arr") int lTLength = v + i;
	}

	void subLTLengthB(@LTLength("arrB") int v) {
		// Show result is of type Unknown
		@Unknown int nn1 = v + i;
		//:: error (assignment.type.incompatible)
		@NonNegative int nn = v + i;
		//:: error (assignment.type.incompatible)
		@LTLength("arr") int lTLength = v + i;
	}
}