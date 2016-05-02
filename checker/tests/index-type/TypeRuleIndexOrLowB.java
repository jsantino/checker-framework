class TypeRuleIndexOrLowB {
  
	int arr = new int[5];
	int arrB = new int[5];
	
	// TODO: define error
	//:: error :
	int accessArray (@IndexOrLow("arrB") int i) {
		return arr[i];
	}
}
