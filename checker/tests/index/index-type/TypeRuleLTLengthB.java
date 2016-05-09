import org.checkerframework.checker.index.qual.*;

class TypeRuleLTLengthB {
	int[] arr = new int[5];
	int[] arrB = new int[5];
	
	int accessArray (@LTLength("arrB") int i) {
		//:: warning: (Potentially unsafe array access: only use @IndexFor as index. Found: @LTLength("arrB") int)
		return arr[i];
	}
}
