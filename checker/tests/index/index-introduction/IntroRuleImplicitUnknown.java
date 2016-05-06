import org.checkerframework.checker.index.qual.*;

class IntroRuleImplicitIndexOrHigh {
	
	int[] arr = new int[5];
	
	void foo() {
		
		int v1 = -2; 
		int v2 = -10;
		int v3 = Integer.MIN_VALUE;
		float f = (float) -2.0;
		int v4 = (int) f;
		
		// Idea: prove type of variable v by assigning it to a variable of type t and assigning a variable of type t to v
		@Unknown int u1 = -20;
		@Unknown int u2 = -20;
		
		// Prove type of v1,v2,v2 is subtype of Unknown
		u1 = v1;
		u1 = v2;
		u1 = v3;
		u1 = v4;
		
		// Prove Unknown is subtype of type of v1,v2,v3
		v1 = u2;
		v2 = u2;
		v3 = u2;
		v4 = u2;
		
	}
}