import org.checkerframework.checker.nonneg.*;
import org.checkerframework.checker.nonneg.qual.*;

//@Trivial.qual.DefaultQualifier(Unknown.class)


/*
 * These test cases test to make sure that the qualifier hierarchy is
 * correctly enforced using inline annotations.
 */
public class QualifierHierarchy {

    void testImplicitDecloration() {
    	@NonNegative int a = 1;
    	@NonNegative int b = 2;
    	@Unknown int c = -1;
    	@Unknown int d = 3;
    	
        //:: error: (assignment.type.incompatible)
    	@NonNegative int e = -1;
    }

    public void test() {
        test(NonNull.class);

        @NonNegative int n1 = 1;
        test(n1);
        @Unknown int n2 = -1;
        //:: warning: (argument.type.incompatible)
        test(n2);
        
        @Unknown int n3 = 2;
        //:: warning: (assignment.type.incompatible)
        @NonNegative int n4 = n3;

    }
}