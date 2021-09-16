package org.example;

import com.louis.springframework.aop.AdvisedSupport;
import com.louis.springframework.aop.ClassFilter;
import com.louis.springframework.aop.MethodMatcher;
import com.louis.springframework.aop.TargetSource;
import com.louis.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.louis.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.louis.springframework.aop.framework.ProxyFactory;
import com.louis.springframework.aop.framework.ReflectiveMethodInvocation;
import com.louis.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import com.louis.springframework.context.support.ClassPathXmlApplicationContext;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @date : 2021/8/16
 */
public class ApiTest {

    @Test
    public void testAop() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-simple.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);

        System.out.println("test result: " + userService.queryUserInfo());
    }


    @Test
    public void testProxyMethod() {
        Object targetObj = new UserService();

        IUserService proxy = (IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), targetObj.getClass().getInterfaces(), new InvocationHandler() {

            MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* org.example.IUserService.*(..))");

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (methodMatcher.matches(method, targetObj.getClass())) {
                    MethodInterceptor methodInterceptor = invocation -> {
                        long start = System.currentTimeMillis();
                        try {
                            return invocation.proceed();
                        } finally {
                            System.out.println("监控 - Begin By AOP");
                            System.out.println("方法名称：" + invocation.getMethod().getName());
                            System.out.println("方法耗时：" + (System.currentTimeMillis() - start) + "ms");
                            System.out.println("监控 - End\r\n");
                        }
                    };

                    return methodInterceptor.invoke(new ReflectiveMethodInvocation(targetObj, method, args));
                }
                return method.invoke(targetObj, args);
            }
        });

        String result = proxy.queryUserInfo();
        System.out.println("test result: " + result);
        proxy.register("Louis");
    }

    @Test
    public void testAdvisor() {
        UserService userService = new UserService();
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression("execution(* org.example.IUserService.*(..))");
        advisor.setAdvice(new MethodBeforeAdviceInterceptor(new UserServiceBeforeAdvice()));

        ClassFilter classFilter = advisor.getPointcut().getClassFilter();
        if (classFilter.matches(userService.getClass())) {
            AdvisedSupport advisedSupport = new AdvisedSupport();
            TargetSource targetSource = new TargetSource(userService);
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setProxyTargetClass(true);
            IUserService proxy = (IUserService) new ProxyFactory(advisedSupport).getProxy();
            System.out.println("test result: " + proxy.queryUserInfo());
        }
    }
}
