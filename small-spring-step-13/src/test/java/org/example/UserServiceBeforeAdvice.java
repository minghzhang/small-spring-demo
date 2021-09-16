package org.example;

import com.louis.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @date : 2021/9/16
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截方法：" + method.getName());
    }
}
