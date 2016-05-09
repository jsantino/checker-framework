import org.checkerframework.checker.index.qual.*;

class TypeRuleLTLength {
	int[] arr = new int[5];
	
	int accessArray (@LTLength("arr") int i) {
		//:: warning: (Potentially unsafe array access: only use @IndexFor as index. Found: @LTLength("arr") int)
		return arr[i];
	}
}
