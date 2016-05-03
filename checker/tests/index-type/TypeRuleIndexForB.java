import org.checkerframework.checker.index.qual.*;

class TypeRuleIndexForB {
  
	int[] arr = new int[5];
	int[] arrB = new int[5];
	
	// TODO: define error
	//:: error :
	int accessArray (@IndexFor("arrB") int i) {
		return arr[i];
	}
}
