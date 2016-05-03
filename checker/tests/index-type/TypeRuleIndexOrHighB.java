import org.checkerframework.checker.index.qual.*;

class TypeRuleIndexOrHighB {
  
	int arr = new int[5];
	int arrB = new int[5];
	
	// TODO: define error
	//:: error :
	int accessArray (@IndexOrHigh("arrB") int i) {
		return arr[i];
	}
}
