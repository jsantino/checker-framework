class IntroRuleLTLength.java {
	
	int[] arr = new int[5];
	
	void foo() {
		@LTLength("arr") int v1 = -2;
		@LTLength("arr") int v2 = -10;
		@LTLength("arr") int v3 = Integer.MIN_VALUE;
		float f = -2.0;
		@NonNegative int v4 = (int) f;
	}
	
	void foo2() {
		@Unknown int v1 = -2;
		@Unknown int v2 = -10;
		@Unknown int v3 = Integer.MIN_VALUE;
		float f = -2.0;
		@Unknown int v4 = (int) f;
	}
}