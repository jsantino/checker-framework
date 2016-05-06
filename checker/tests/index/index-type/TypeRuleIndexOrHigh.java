import org.checkerframework.checker.index.qual.*;

class TypeRuleIndexOrHigh {
  
	int[] arr = new int[5];
	
	int accessArray (@IndexOrHigh("arr") int i) {
		//:: warning: (Potentially unsafe array access: only use @IndexFor as index. Found: @IndexOrHigh("arr") int)
		return arr[i];
	}
}
