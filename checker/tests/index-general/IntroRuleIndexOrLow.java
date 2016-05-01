class IntroRuleIndexOrLow.java {
	
	int[] arr = new int[5];
	
	void foo() {
		@IndexOrLow("arr") int v1 = -1;
	}
	
	void foo2() {
		@LTLength("arr") int v2 = -1;
		@Unknown int v3 = -1;
	}
}