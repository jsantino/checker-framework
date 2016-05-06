import org.checkerframework.checker.index.qual.*;

class IntroRuleImplicitNonNegative {
	
	void foo() {
		
		// int v1 = 0; This case is to be discusses. Unsure whether 0 should be IndexOrHigh or NonNegative.
		int v1 = 1;
		// Integer.Max_Value is not an int literal it is a field...
		//int v2 = Integer.MAX_VALUE;
		
		// Idea: prove type of variable v by assigning it to a variable of type t and assigning a variable of type t to v
		@NonNegative int nn1 = 10;
		@NonNegative int nn2 = 10;
		
		// Prove type of v1,v2 is subtype of NonNegative
		nn1 = v1;
		//nn1 = v2;
		
		// Prove NonNegative is subtype of type of v1,v2
		v1 = nn2;
		//v2 = nn2;
		
	}
}