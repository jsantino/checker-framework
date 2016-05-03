package tests;

import org.checkerframework.framework.test.CheckerFrameworkTest;

import java.io.File;

import org.junit.runners.Parameterized.Parameters;

/**
 * JUnit tests for the NonNeg checker when reflection resolution is enabled
 */
public class NonNegTest extends CheckerFrameworkTest {

    public NonNegTest(File testFile) {
        super(testFile,
                org.checkerframework.checker.nonneg.NonNegChecker.class,
                "nonneg",
                "-Anomsgtext");
    }

    @Parameters
    public static String[] getTestDirs() {
        return new String[]{"nonneg"};
    }

}
