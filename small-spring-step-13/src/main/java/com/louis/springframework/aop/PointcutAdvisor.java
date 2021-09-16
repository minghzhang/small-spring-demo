package com.louis.springframework.aop;

/**
 * @date : 2021/9/16
 */
public interface PointcutAdvisor extends Advisor {

    /**
     * Get the Pointcut that drives this advisor.
     *
     * @return
     */
    Pointcut getPointcut();
}
