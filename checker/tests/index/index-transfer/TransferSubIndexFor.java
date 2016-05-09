import org.checkerframework.checker.index.qual.IndexFor;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.IndexOrLow;
import org.checkerframework.checker.index.qual.LTLength;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.Unknown;

class TransferSubIndexFor {

	
	int[] arr = new int[5];
	int[] arrB = new int[5];
	
	// This error can be suppressed
	//:: error: (assignment.type.incompatible)
	@IndexFor("arr") int i = 2;
	
	void subIndexFor(@IndexFor("arr") int v, @LTLength("arr") int lTLength1, @LTLength("arr") int lTLength2) {
		
		int result = v - i;
		
		// Show result is of type LTLength
		LTLength1 = result;
		result = LTLength2;
	}

	void subIndexOrLow(@IndexOrLow("arr") int v) {
		@Unknown int unknown1;
		@Unknown int unknown2 = -10;
		
		int result = v - i;
		
		// Show result is of type Unknown
		unknown1 = result;
		result = unknown2;
	}
	
	void subIndexOrHigh(@IndexOrHigh("arr") int v, @LTLength("arr") int lTLength1, @LTLength("arr") int lTLength2) {
		
		int result = v - i;
		
		// Show result is of type LTLength
		lTLength1 = result;
		result = lTLength2;
	}
	
	void sub1(@IndexOrLow("arr") int indexOrLow1, @IndexOrLow("arr") int indexOrLow2) {
		
		int result = i - 1;
		
		// Show result is of type IndexOrLow
		indexOrLow1 = result;
		result = indexOrLow2;
	}
	
	void sub0(@IndexFor("arr") int indexFor1, @IndexFor("arr") int indexFor2) {

		int result = i - 0;
		
		// Show result is of type IndexFor
		indexFor1 = result;
		result = indexFor2;
	}
	
	void subLTLength(@LTLength("arr") int v) {
		@Unknown int unknown1;
		@Unknown int unknown2 = -10;
		
		int result = v - i;
		
		// Show result is of type Unknown
		unknown1 = result;
		result = unknown2;
	}
	
	void subNonNegative(@NonNegative int v, @LTLength("arr") int lTLength1, @LTLength("arr") int lTLength2) {
		
		int result = v - i;
		
		// Show result is of type LTLength
		lTLength1 = result;
		result = lTLength2;
	}

	void subUnknown(@Unknown int v) {
		@Unknown int unknown1;
		@Unknown int unknown2 = -10;
		
		int result = v - i;
		
		// Show result is of type Unknown
		unknown1 = result;
		result = unknown2;
	}

	void subIndexForB(@IndexFor("arrB") int v, @LTLength("arr") int lTLength1, @LTLength("arr") int lTLength2) {
		
		int result = v - i;
		
		// Show result is of type LTLength
		lTLength1 = result;
		result = lTLength2;
	}
	
	void subIndexOrLowB(@IndexOrLow("arrB") int v) {
		@Unknown int unknown1;
		@Unknown int unknown2 = -10;
		
		int result = v - i;
		
		// Show result is of type Unknown
		unknown1 = result;
		result = unknown2;
	}

	void subIndexOrHighB(@IndexOrHigh("arrB") int v, @LTLength("arr") int lTLength1, @LTLength("arr") int lTLength2) {
		
		int result = v - i;
		
		// Show result is of type LTLength
		lTLength1 = result;
		result = lTLength2;
	}

	void subLTLengthB(@LTLength("arrB") int v) {
		@Unknown int unknown1;
		@Unknown int unknown2 = -10;
		
		int result = v - i;
		
		// Show result is of type Unknown
		unknown1 = result;
		result = unknown2;
	}
}