import org.checkerframework.checker.index.qual.IndexFor;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.IndexOrLow;
import org.checkerframework.checker.index.qual.LTLength;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.Unknown;

class TransferAdd0 {

	
	int[] arr = new int[5];
	int[] arrB = new int[5];
	
	void addIndexFor(@IndexFor("arr") int v, @IndexFor("arr") int indexFor1, @IndexFor("arr") int indexFor2) {
		
		int result = 0 + v;
		
		// Show result is of type IndexFor
		indexFor1 = result;
		result = indexFor2;
	}

	void addIndexOrLow(@IndexOrLow("arr") int v, @IndexOrLow("arr") int indexOrLow1, @IndexOrLow("arr") int indexOrLow2) {
		
		int result = 0 + v;
		
		// Show result is of type IndexOrLow
		indexOrLow1 = result;
		result = indexOrLow2;
	}
	
	void addIndexOrHigh(@IndexOrHigh("arr") int v, @IndexOrHigh("arr") int indexOrHigh1, @IndexOrHigh("arr") int indexOrHigh2) {
		
		int result = 0 + v;
		
		// Show result is of type IndexorHigh
		indexOrHigh1 = result;
		result = indexOrHigh2;
	}
	
	void add1() {
		@NonNegative int nn1;
		@NonNegative int nn2 = 10;
		
		int result = 0 + 1;
		
		// Show result is of type NonNegative
		nn1 = result;
		result = nn2;
	}
	
	void add0() {
		@NonNegative int nn1;
		@NonNegative int nn2 = 10;
		
		int result = 0 + 0;
		
		// Show result is of type NonNegative
		nn1 = result;
		result = nn2;
	}
	
	void addLTLength(@LTLength("arr") int v, @LTLength("arr") int lTLength1, @LTLength("arr") int lTLength2) {
		@Unknown int unknown1;
		@Unknown int unknown2 = -10;
		
		int result = 0 + v;
		
		// Show result is of type Unknown
		lTLength1 = result;
		result = lTLength2;
	}
	
	void addNonNegative(@NonNegative int v) {
		@NonNegative int nn1 = 10;
		@NonNegative int nn2 = 10;
		
		int result = 0 + v;
		
		// Show result is of type NonNegative
		nn1 = result;
		result = nn2;
	}

	void addUnknown(@Unknown int v) {
		@Unknown int unknown1;
		@Unknown int unknown2 = -10;
		
		int result = 0 + v;
		
		// Show result is of type Unknown
		unknown1 = result;
		result = unknown2;
	}
}