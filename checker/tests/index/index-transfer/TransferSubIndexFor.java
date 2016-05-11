import org.checkerframework.checker.index.qual.IndexFor;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.IndexOrLow;
import org.checkerframework.checker.index.qual.LTLength;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.Unknown;

class TransferSubIndexFor {

	
	int[] arr = new int[5];
	int[] arrB = new int[5];
	
	// TODO: REPLACE v + i by v - i
	
	// This error can be suppressed
	//:: error: (assignment.type.incompatible)
	@IndexFor("arr") int i = 2;
	
	void subIndexFor(@IndexFor("arr") int v, @LTLength("arr") int LTLength1, @LTLength("arr") int LTLength2) {
		
		int result = v - i;
		
		// Show result is of type LTLength
		LTLength1 = result;
		result = LTLength2;
	}

	void subIndexOrLow(@IndexOrLow("arr") int v) {
		// Show result is of type Unknown
		@Unknown int nn1 = v + i;
		//:: error (assignment.type.incompatible)
		@NonNegative int nn = v + i;
		//:: error (assignment.type.incompatible)
		@LTLength("arr") int lTLength = v + i;
	}
	
	// not a valid Transfer, ex. v = a.length and i = 0; v is not < length;
	void subIndexOrHigh(@IndexOrHigh("arr") int v, @LTLength("arr") int lTLength1, @LTLength("arr") int lTLength2) {
		
		int result = v - i;
		
		// Show result is of type LTLength
		//:: error: (assignment.type.incompatible)
		lTLength1 = result;
		result = lTLength2;
	}
	
	void sub1() {
		@IndexOrLow("arr") int indexOrLow = i - 1;
		//:: error: (assignment.type.incompatible)
		@IndexFor("arr") int indexFor = i - 1;
	}
	
	void sub0() {
		@IndexFor("arr") int indexFor = i - 0;
	}
	
	void subLTLength(@LTLength("arr") int v) {
		// Show result is of type Unknown
		@Unknown int nn1 = v + i;
		//:: error (assignment.type.incompatible)
		@NonNegative int nn = v + i;
		//:: error (assignment.type.incompatible)
		@LTLength("arr") int lTLength = v + i;
	}
	
	// ex. v = 2* a.length and i = 0, v is not < length
	void subNonNegative(@NonNegative int v, @LTLength("arr") int lTLength1, @LTLength("arr") int lTLength2) {
		
		int result = v - i;
		
		// Show result is of type LTLength
		//:: error: (assignment.type.incompatible)
		lTLength1 = result;
		result = lTLength2;
	}

	void subUnknown(@Unknown int v) {
		// Show result is of type Unknown
		@Unknown int nn1 = v + i;
		//:: error (assignment.type.incompatible)
		@NonNegative int nn = v + i;
		//:: error (assignment.type.incompatible)
		@LTLength("arr") int lTLength = v + i;
	}
	
	// not valid transfer, different arrays are different bounds
	void subIndexForB(@IndexFor("arrB") int v, @LTLength("arr") int lTLength1, @LTLength("arr") int lTLength2) {
		
		int result = v - i;
		
		// Show result is of type LTLength
		//:: error: (assignment.type.incompatible)
		lTLength1 = result;
		result = lTLength2;
	}
	
	void subIndexOrLowB(@IndexOrLow("arrB") int v) {
		// Show result is of type Unknown
		@Unknown int nn1 = v + i;
		//:: error (assignment.type.incompatible)
		@NonNegative int nn = v + i;
		//:: error (assignment.type.incompatible)
		@LTLength("arr") int lTLength = v + i;
	}

	void subIndexOrHighB(@IndexOrHigh("arrB") int v, @LTLength("arr") int lTLength1, @LTLength("arr") int lTLength2) {
		// Show result is of type LTLength
				@LTLength("arr") lTLength1 = i - v;
				//:: error (assignment.type.incompatible)
				@IndexOrLow("arr") indexOrLow = i - v;
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