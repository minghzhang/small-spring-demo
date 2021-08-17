package org.example;

import com.louis.springframework.beans.factory.config.BeanDefinition;
import com.louis.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

/**
 * @author : landon
 * @date : 2021/8/16
 */
public class ApiTest {

    @Test
    public void testBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);

        beanFactory.registerBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService", "Louis");
        userService.queryUserInfo();

        UserService userService_singleton = (UserService) beanFactory.getBean("userService", "Louis");
        userService_singleton.queryUserInfo();
    }
}
