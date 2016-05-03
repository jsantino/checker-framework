import org.checkerframework.checker.index.qual.*;

class TypeRuleIndexOrLow {
  
	int arr = new int[5];
	
	// TODO: define error
	//:: error :
	int accessArray (@IndexOrLow("arr") int i) {
		return arr[i];
	}
}
