package com.sample.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used by LoggerExecutionAdvice to build an aspect and do logs in
 * the begin, end and in the exception of every method annotated with @LogExecution
 *
 * <ul>
 * <li>Only work in public methods</li>
 * <li>Does'nt work in nested calls</li>
 * </ul>

 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface LogExecution {

}
