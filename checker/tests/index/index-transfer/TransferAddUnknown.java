import org.checkerframework.checker.index.qual.IndexFor;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.IndexOrLow;
import org.checkerframework.checker.index.qual.LTLength;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.Unknown;

class TransferAddLTLength {

	
	int[] arr = new int[5];
	int[] arrB = new int[5];
	
	// This error can be suppressed
	//:: error: (assignment.type.incompatible)
	@LTLength("arr") int i = 2;
	
	void addIndexFor(@IndexFor("arr") int v) {
		@Unknown int unknown1 = -5;
		@Unknown int unknown2 = -10;
		
		int result = v + i;
		
		// Show result is of type Unknown
		unknown1 = result;
		result = unknown2;
	}

	void addIndexOrLow(@IndexOrLow("arr") int v) {
		@Unknown int unknown1;
		@Unknown int unknown2 = -10;
		
		int result = v + i;
		
		// Show result is of type Unknown
		unknown1 = result;
		result = unknown2;
	}
	
	void addIndexOrHigh(@IndexOrHigh("arr") int v) {
		@Unknown int unknown1;
		@Unknown int unknown2 = -10;
		
		int result = v + i;
		
		// Show result is of type Unknown
		unknown1 = result;
		result = unknown2;
	}
	
	void add1() {
		@Unknown int unknown1;
		@Unknown int unknown2 = -10;
		
		int result = i + 1;
		
		// Show result is of type IndexOrHigh
		unknown1 = result;
		result = unknown2;
	}
	
	void add0() {
		@Unknown int unknown1;
		@Unknown int unknown2 = -10;
		
		int result = i + 0;
		
		// Show result is of type IndexOrLow
		unknown1 = result;
		result = unknown2;
	}
	
	void addLTLength(@LTLength("arr") int v) {
		@Unknown int unknown1;
		@Unknown int unknown2 = -10;
		
		int result = v + i;
		
		// Show result is of type Unknown
		unknown1 = result;
		result = unknown2;
	}
	
	void addNonNegative(@NonNegative int v) {
		@Unknown int unknown1;
		@Unknown int unknown2 = -10;
		
		int result = v + i;
		
		// Show result is of type Unknown
		unknown1 = result;
		result = unknown2;
	}

	void addUnknown(@Unknown int v) {
		@Unknown int unknown1;
		@Unknown int unknown2 = -10;
		
		int result = v + i;
		
		// Show result is of type Unknown
		unknown1 = result;
		result = unknown2;
	}

	void addIndexForB(@IndexFor("arrB") int v) {
		@Unknown int unknown1;
		@Unknown int unknown2 = -10;
		
		int result = v + i;
		
		// Show result is of type NonNegative
		unknown1 = result;
		result = unknown2;
	}
	
	void addIndexOrLowB(@IndexOrLow("arrB") int v) {
		@Unknown int unknown1;
		@Unknown int unknown2 = -10;
		
		int result = v + i;
		
		// Show result is of type Unknown
		unknown1 = result;
		result = unknown2;
	}

	void addIndexOrHighB(@IndexOrHigh("arrB") int v) {
		@Unknown int unknown1 = 10;
		@Unknown int unknown2 = 10;
		
		int result = v + i;
		
		// Show result is of type NonNegative
		unknown1 = result;
		result = unknown2;
	}

	void addLTLengthB(@LTLength("arrB") int v) {
		@Unknown int unknown1;
		@Unknown int unknown2 = -10;
		
		int result = v + i;
		
		// Show result is of type Unknown
		unknown1 = result;
		result = unknown2;
	}
}