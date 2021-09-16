package com.louis.springframework.aop;

import java.lang.reflect.Method;

/**
 * @date : 2021/9/16
 */
public interface MethodBeforeAdvice extends BeforeAdvice {

    /**
     * Callback before a given method is invoked.
     *
     * @param method method being invoked
     * @param args   arguments to the method
     * @param target target of the method invocation,
     * @throws Throwable
     */
    void before(Method method, Object[] args, Object target) throws Throwable;
}
