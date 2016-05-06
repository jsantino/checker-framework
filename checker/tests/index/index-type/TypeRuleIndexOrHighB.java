import org.checkerframework.checker.index.qual.*;

class TypeRuleIndexOrHighB {
  
	int[] arr = new int[5];
	int[] arrB = new int[5];
	
	int accessArray (@IndexOrHigh("arrB") int i) {
		//:: warning: (Potentially unsafe array access: only use @IndexFor as index. Found: @IndexOrHigh("arrB") int)
		return arr[i];
	}
}
