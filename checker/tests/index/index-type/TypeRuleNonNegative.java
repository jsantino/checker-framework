import org.checkerframework.checker.index.qual.*;

class TypeRuleNonNegative {
  
	int[] arr = new int[5];
	
	int accessArray (@NonNegative("arr") int i) {
		//:: warning: (Potentially unsafe array access: only use @IndexFor as index. Found: @NonNegative("arr") int)
		return arr[i];
	}
}
