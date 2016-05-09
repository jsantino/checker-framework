import org.checkerframework.checker.index.qual.*;

class TypeRuleIndexOrLowB {
	int[] arr = new int[5];
	int[] arrB = new int[5];
	
	int accessArray (@IndexOrLow("arrB") int i) {
		//:: warning: (Potentially unsafe array access: only use @IndexFor as index. Found: @IndexOrLow("arrB") int)
		return arr[i];
	}
}
