import org.checkerframework.checker.index.qual.*;

class IntroRuleIndexOrLow{
	
	int[] arr = new int[5];
	
	void foo() {
		@IndexOrLow("") int v1 = -1;
	}
	
	void foo2() {
		@LTLength("") int v2 = -1;
		@Unknown int v3 = -1;
	}
}