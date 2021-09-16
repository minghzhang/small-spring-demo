package com.louis.springframework.aop.aspectj;

import org.example.UserService;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @date : 2021/9/15
 */
public class AspectJExpressionPointcutTest {

    @Test
    public void matches() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* org.example.UserService.*(..))");
        Class<UserService> clazz = UserService.class;
        Method method = clazz.getDeclaredMethod("queryUserInfo");

        Class<AspectJExpressionPointcutTest> aspectJExpressionPointcutTestClass = AspectJExpressionPointcutTest.class;
        System.out.println(pointcut.matches(clazz));
        System.out.println(pointcut.matches(method, clazz));

        Method method1 = aspectJExpressionPointcutTestClass.getDeclaredMethod("matches");
        System.out.println(pointcut.matches(aspectJExpressionPointcutTestClass));
        System.out.println(pointcut.matches(method,aspectJExpressionPointcutTestClass));

    }

    @Test
    public void testMatches() {
    }
}