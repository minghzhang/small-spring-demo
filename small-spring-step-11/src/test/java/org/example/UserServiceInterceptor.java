package org.example;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @date : 2021/9/15
 */
public class UserServiceInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return invocation.proceed();
        } finally {
            System.out.println("monitor - Begin By AOP");
            System.out.println("method name: " + invocation.getMethod());
            System.out.println("method costTime:" + (System.currentTimeMillis() - start) + "ms");
            System.out.println("monitor - End\r\n");
        }
    }
}
