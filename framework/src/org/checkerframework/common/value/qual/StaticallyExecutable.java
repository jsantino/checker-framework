package org.checkerframework.common.value.qual;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * StaticallyExecutable is a method annotation that indicates that
 * the compiler is allowed to run the method at compile time, if all of
 * the method's arguments are compile-time constants.
 * It is used by the Constant Value Checker.
 *
 * @checker_framework.manual #constant-value-checker Constant Value Checker
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface StaticallyExecutable {}
