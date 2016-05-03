import org.checkerframework.checker.index.qual.*;

class TypeRuleIndexOrHigh {
  
	int arr = new int[5];
	
	// TODO: define error
	//:: error :
	int accessArray (@IndexOrHigh("arr") int i) {
		return arr[i];
	}
}
