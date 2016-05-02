class TypeRuleIndexFor {
  
	int arr = new int[5];
	
	int accessArray (@IndexFor("arr") int i) {
		return arr[i];
	}
}