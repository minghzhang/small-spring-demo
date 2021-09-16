package com.louis.springframework.aop;

import java.lang.reflect.Method;

/**
 * @date : 2021/9/15
 */
public interface MethodMatcher {

    boolean matches(Method method, Class<?> targetClass);
}
