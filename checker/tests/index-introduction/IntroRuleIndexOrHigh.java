class IntroRuleIndexOrHigh.java {
	
	int[] arr = new int[5];
	
	void foo() {
		@IndexOrHigh("arr") int v1 = arr.length;
		@IndexOrHigh("arr") int v2 = 0;
	}
	
	void foo2() {
		@NonNegative int v1 = arr.length;
		@Unknown int v3 = arr.length;
	}
}