import org.checkerframework.checker.index.qual.IndexFor;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.IndexOrLow;
import org.checkerframework.checker.index.qual.LTLength;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.Unknown;

class TransferAddNonNegative {

	
	int[] arr = new int[5];
	int[] arrB = new int[5];
	
	// The NonNegative variable.
	@NonNegative int i = 2;
	
	void addIndexFor(@IndexFor("arr") int v) {
		@NonNegative int nn1 = 10;
		@NonNegative int nn2 = 10;
		
		int result = v + i;
		
		// Show result is of type NonNegative
		nn1 = result;
		result = nn2;
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
		@NonNegative int nn1 = 10;
		@NonNegative int nn2 = 10;
		
		int result = v + i;
		
		// Show result is of type NonNegative
		nn1 = result;
		result = nn2;
	}
	
	void add1() {
		@NonNegative int nn1;
		@NonNegative int nn2 = 10;
		
		int result = i + 1;
		
		// Show result is of type IndexOrHigh
		nn1 = result;
		result = nn2;
	}
	
	void add0() {
		@NonNegative int nn1;
		@NonNegative int nn2 = 10;

		int result = i + 0;
		
		// Show result is of type IndexOrHigh
		nn1 = result;
		result = nn2;
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
		@NonNegative int nn1 = 10;
		@NonNegative int nn2 = 10;
		
		int result = v + i;
		
		// Show result is of type NonNegative
		nn1 = result;
		result = nn2;
	}

	void addUnknown(@Unknown int v) {
		@Unknown int unknown1;
		@Unknown int unknown2 = -10;
		
		int result = v + i;
		
		// Show result is of type Unknown
		unknown1 = result;
		result = unknown2;
	}
}