import org.checkerframework.checker.nonneg.*;
import org.checkerframework.checker.nonneg.qual.*;

/*
 * These test cases test to make sure that the qualifier hierarchy is
 * correctly enforced using inline annotations.
 */
public class QualifierHierarchy {

    void testUserWrittenDeclaration() {
    	@NonNegative int a = 0;
    	@NonNegative int b = 1;
    	
    	@Unknown int c = -1;
    	@Unknown int d = 3;
    	
        //:: warning: (assignment.type.incompatible)
    	@NonNegative int e = -1;
    }
    
    void testUserWrittenAssignment() {
    	@NonNegative int a = 1;
    	@NonNegative int b = a;
    	
    	@Unknown int c = -1;
    	@Unknown int d = c;
    	@Unknown int e = a;
    	
    	//:: warning: (assignment.type.incompatible)
    	@NonNegative int f = c;
    }
    
    void testUserWritten () {
    	
    }

    void testImplicitAnnotations() {
    	@NonNegative int a = 1;

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