package com.louis.springframework.aop;

/**
 * @date : 2021/9/15
 */
public interface Pointcut {

    /**
     * return the ClassFilter for this pointcut
     *
     * @return
     */
    ClassFilter getClassFilter();

    /**
     * return the MethodMatcher for this pointcut
     *
     * @return
     */
    MethodMatcher getMethodMatcher();
}
