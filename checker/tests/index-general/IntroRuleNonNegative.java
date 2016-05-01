class IntroRuleNonNegative.java {
	
	void foo() {
		@NonNegative int v1 = 0;
		@NonNegative int v2 = 10;
		@NonNegative int v3 = Integer.MAX_VALUE;
		float f = 0.0;
		@NonNegative int v4 = (int) f;
	}
	
	void foo2() {
		@Unknown int v1 = 0;
		@Unknown int v2 = 10;
		@Unknown int v3 = Integer.MAX_VALUE;
		@Unknown int v4 = (int) f;
	}
}