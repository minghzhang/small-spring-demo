package com.louis.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * @date : 2021/9/16
 */
public interface Advisor {

    /** return the advice part of this aspect. An advice may be an interceptor, a before advice, a throws advice etc.
     * @return
     */
    Advice getAdvice();
}
