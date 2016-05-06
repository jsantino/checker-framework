package tests;

import org.checkerframework.framework.test.CheckerFrameworkTest;

import java.io.File;

import org.junit.runners.Parameterized.Parameters;

/**
 * JUnit tests for the Index checker.
 */
public class IndexTest extends CheckerFrameworkTest {

    public IndexTest(File testFile) {
        super(testFile,
                org.checkerframework.checker.index.IndexChecker.class,
                "index",
                "-Anomsgtext");
    }

    @Parameters
    public static String[] getTestDirs() {
        return new String[]{"index"};
    }

}
