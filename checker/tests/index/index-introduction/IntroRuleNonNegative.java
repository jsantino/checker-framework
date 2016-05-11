import org.checkerframework.checker.index.qual.*;

class IntroRuleNonNegative{
	
	void foo() {
		@NonNegative int v1 = 1;
		@NonNegative int v2 = 100;
		
		// prove super indexOrHigh
		//:: error:(assignment.type.incompatible)
		@IndexOrHigh("") int IoH = v1;
		//:: error:(assignment.type.incompatible)
		IoH = v2;
	}
	
}