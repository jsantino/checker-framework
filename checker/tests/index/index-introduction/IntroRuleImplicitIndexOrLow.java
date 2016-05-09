import org.checkerframework.checker.index.qual.*;

//Doesn't test anything yet.

class IntroRuleImplicitIndexOrHigh {
	
	int[] arr = new int[5];
	
	void foo() {
		
		int v1 = -1;
		
		// Idea: prove type of variable v by assigning it to a variable of type t and assigning a variable of type t to v
		@IndexOrLow("arr") int indexOrLow1 = arr.length -1;
		@IndexOrLow("arr") int indexOrLow2 = arr.length -1;
		
		// Prove type of v1 is subtype of IndexOrLow
		//@IndexOrLow("") int indexOrLow1 = v1;
		//@IndexOrLow("") int indexOrLow2 = -1;
		// Prove IndexOrLow is subtype of type of v1
		v1 = indexOrLow2;
		
	}
}