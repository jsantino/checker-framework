import org.checkerframework.checker.index.qual.*;

class TypeRuleIndexForB {
  
	int[] arr = new int[5];
	int[] arrB = new int[5];
	
	int accessArray (@IndexFor("arrB") int i) {
		//:: warning: (Potentially unsafe array access: only use IndexFor(arr) index. Found: @IndexFor("arrB") int)
		return arr[i];
	}
}
