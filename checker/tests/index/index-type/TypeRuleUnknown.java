import org.checkerframework.checker.index.qual.*;

class TypeRuleUnknown {
	int[] arr = new int[5];
	
	int accessArray (@Unknown int i) {
		//:: warning: (Potentially unsafe array access: only use @IndexFor as index. Found: @Unknown int)
		return arr[i];
	}
}
