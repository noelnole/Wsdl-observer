package com.sample.aop;

import com.google.common.base.Stopwatch;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * This class is base in aspects.
 * The goal of the class is generate a message in the begin and in the end
 * of every method
 *
 */
@Aspect
@Component
public class LoggerExecutionAdvice {
    private static final String TRACE_BEGIN = "%s - Method parameters: ";
    private static final String TRACE_END = "%s - Method return: ";
    private static final String TRACE_EXCEPTION = "%s - Exception in method: ";
    private static final String TRACE_METHOD = "%s - ";
    private static final String DELIM_STR = "'{}'";
    private static final String SEPARATOR = ",";
    private static final String VOID = "(void)";

    @Around(value = "execution( @com.sample.aop.LogExecution * *(..))")
    public Object logTime(ProceedingJoinPoint jp) throws Throwable {
        String methodName = getMethodName(jp);
        Logger logger = LoggerFactory.getLogger(jp.getTarget().getClass());
        begin(logger, methodName, jp.getArgs());
        Stopwatch stopWatch = Stopwatch.createStarted();
        Object result = null;
        try {
            result = jp.proceed();
            return result;
        } catch (Throwable t) {
            exception(logger, methodName, t.getMessage(), t);
            throw t;
        } finally {
            stopWatch.stop();
            end(logger, methodName, result);
            debug(logger, methodName, "Elapsed time: " + stopWatch);
        }
    }

    private String getMethodName(JoinPoint p) {
        return new StringBuilder("[").append(p.getSignature().getName()).append("]").toString();
    }

    private void begin(final Logger logger, final String methodName, final Object... params) {
        if (logger.isDebugEnabled()) {
            logger.debug(
                String.format(TRACE_BEGIN, methodName) + (params != null && params.length > 0 ?
                    StringUtils.repeat(DELIM_STR, SEPARATOR, params.length) :
                    VOID), params);
        }
    }

    private void end(final Logger logger, final String methodName, final Object... params) {
        if (logger.isDebugEnabled()) {
            logger.debug(
                String.format(TRACE_END, methodName) + (params != null && params.length > 0 ?
                    StringUtils.repeat(DELIM_STR, SEPARATOR, params.length) :
                    VOID), params);
        }
    }

    private void debug(final Logger logger, final String methodName, final String message,
        final Object... params) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format(TRACE_METHOD, methodName) + message, params);
        }
    }

    private void exception(final Logger logger, final String methodName, final String message,
        final Throwable throwable) {
        if (logger.isErrorEnabled()) {
            logger.error(String.format(TRACE_EXCEPTION, methodName), message, throwable);
        }
    }
}
