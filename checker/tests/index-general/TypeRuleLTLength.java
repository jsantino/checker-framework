class TypeRuleLTLength {
  
	int arr = new int[5];
	
	// TODO: define error
	//:: error :
	int accessArray (@LTLength("arr") int i) {
		return arr[i];
	}
}
