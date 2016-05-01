class TypeRuleNonNegative {
  
	int arr = new int[5];
	
	// TODO: define error
	//:: error :
	int accessArray (@NonNegative("arr") int i) {
		return arr[i];
	}
}
