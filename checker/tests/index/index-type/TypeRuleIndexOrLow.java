import org.checkerframework.checker.index.qual.*;

class TypeRuleIndexOrLow {
	int[] arr = new int[5];
	
	int accessArray (@IndexOrLow("arr") int i) {
		//:: warning: (Potentially unsafe array access: only use @IndexFor as index. Found: @IndexOrLow("arr") int)
		return arr[i];
	}
}
