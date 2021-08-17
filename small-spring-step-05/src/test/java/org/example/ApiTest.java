package org.example;

import com.louis.springframework.beans.PropertyValue;
import com.louis.springframework.beans.PropertyValues;
import com.louis.springframework.beans.factory.config.BeanDefinition;
import com.louis.springframework.beans.factory.config.BeanReference;
import com.louis.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.louis.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.junit.Test;

/**
 *
 * @date : 2021/8/16
 */
public class ApiTest {

    @Test
    public void testBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();


        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

        UserService userService_singleton = (UserService) beanFactory.getBean("userService");
        userService_singleton.queryUserInfo();
    }
}
