package com.louis.springframework.aop;

/**
 * @date : 2021/9/15
 */
public interface ClassFilter {

    boolean matches(Class<?> clazz);
}
