package org.example;

import com.louis.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @date : 2021/8/16
 */
public class ApiTest {


    @Test
    public void testPrototype() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-simple.xml");
        applicationContext.registerShutdownHook();
        UserService userService01 = applicationContext.getBean("userService", UserService.class);
        UserService userService02 = applicationContext.getBean("userService", UserService.class);
        System.out.println(userService01);
        System.out.println(userService02);

        System.out.println("userService01 " + Integer.toHexString(userService01.hashCode()));
        System.out.println("userService02 " + Integer.toHexString(userService02.hashCode()));
    }

    @Test
    public void testFactoryBean() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-simple.xml");
        applicationContext.registerShutdownHook();

        UserService userService = applicationContext.getBean("userService", UserService.class);

        System.out.println("test result:" + userService.queryUserInfo());
    }
}
