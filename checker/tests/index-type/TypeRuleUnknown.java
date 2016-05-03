import org.checkerframework.checker.index.qual.*;

class TypeRuleUnknown {
  
	int arr = new int[5];
	
	// TODO: define error
	//:: error :
	int accessArray (@Unknown int i) {
		return arr[i];
	}
}
