import org.checkerframework.checker.index.qual.*;

//Doesn't test anything yet.

class IntroRuleImplicitIndexOrHigh {
	
	int[] arr = new int[5];
	
	void foo() {
		int[] arrB = new int[5];
		// int v1 = 0; This case is to be discusses. Unsure whether 0 should be IndexOrHigh or NonNegative.
		int v2 = arr.length;
		
		// Idea: prove type of variable v by assigning it to a variable of type t and assigning a variable of type t to v
		@IndexOrHigh("arr") int indexOrHigh1 = arr.length;
		@IndexOrHigh("arr") int indexOrHigh2 = arr.length;
		
		@IndexOrHigh("arrB") int indexOrHigh3 = arrB.length;
		
		// Prove type of v1,v2 is subtype of IndexOrHigh
		indexOrHigh1 = v2;
		
		// Prove NonNegative is subtype of type of v1,v2
		v2 = indexOrHigh2;
		
	}
}