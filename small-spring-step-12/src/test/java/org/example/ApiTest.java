package org.example;

import com.louis.springframework.aop.AdvisedSupport;
import com.louis.springframework.aop.TargetSource;
import com.louis.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.louis.springframework.aop.framework.Cglib2AopProxy;
import com.louis.springframework.aop.framework.JdkDynamicAopProxy;
import org.junit.Test;

/**
 * @date : 2021/8/16
 */
public class ApiTest {

    @Test
    public void testDynamic() {
        IUserService userService = new UserService();

        AdvisedSupport advisedSupport = new AdvisedSupport();


        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution (* org.example.IUserService.*(..))"));

        IUserService proxy_jdk = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        System.out.println("test result: " + proxy_jdk.queryUserInfo());

        IUserService proxy_cglib = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        System.out.println("test result:" + proxy_cglib.register("lewis"));

    }

}
