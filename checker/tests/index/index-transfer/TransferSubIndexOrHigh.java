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
		@Unknown int unknown1;
		@Unknown int unknown2 = -10;
		
		int result = v - i;
		
		// Show result is of type Unknown
		unknown1 = result;
		result = unknown2;
	}

	void subIndexOrLow(@IndexOrLow("arr") int v) {
		@Unknown int unknown1;
		@Unknown int unknown2 = -10;
		
		int result = v - i;
		
		// Show result is of type Unknown
		unknown1 = result;
		result = unknown2;
	}
	
	void subIndexOrHigh(@IndexOrHigh("arr") int v) {
		@Unknown int unknown1;
		@Unknown int unknown2 = -10;
		
		int result = v - i;
		
		// Show result is of type Unknown
		unknown1 = result;
		result = unknown2;
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
		@Unknown int unknown1;
		@Unknown int unknown2 = -10;
		
		int result = v - i;
		
		// Show result is of type Unknown
		unknown1 = result;
		result = unknown2;
	}
	
	void subNonNegative(@NonNegative int v) {
		@Unknown int unknown1;
		@Unknown int unknown2 = -10;
		
		int result = v - i;
		
		// Show result is of type Unknown
		unknown1 = result;
		result = unknown2;
	}

	void subUnknown(@Unknown int v) {
		@Unknown int unknown1;
		@Unknown int unknown2 = -10;
		
		int result = v - i;
		
		// Show result is of type Unknown
		unknown1 = result;
		result = unknown2;
	}

	void subIndexForB(@IndexFor("arrB") int v) {
		@Unknown int unknown1;
		@Unknown int unknown2 = -10;
		
		int result = v - i;
		
		// Show result is of type Unknown
		unknown1 = result;
		result = unknown2;
	}
	
	void subIndexOrLowB(@IndexOrLow("arrB") int v) {
		@Unknown int unknown1;
		@Unknown int unknown2 = -10;
		
		int result = v - i;
		
		// Show result is of type Unknown
		unknown1 = result;
		result = unknown2;
	}

	void subIndexOrHighB(@IndexOrHigh("arrB") int v) {
		@Unknown int unknown1 = 10;
		@Unknown int unknown2 = 10;
		
		int result = v - i;
		
		// Show result is of type Unknown
		unknown1 = result;
		result = unknown2;
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