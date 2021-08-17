package org.example.common;

import com.louis.springframework.beans.BeansException;
import com.louis.springframework.beans.factory.config.BeanPostProcessor;
import org.example.UserService;

/**
 * @author : landon
 * @date : 2021/8/17
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setLocation("Beijing");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
