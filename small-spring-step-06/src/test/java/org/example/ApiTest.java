package org.example;

import com.louis.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.louis.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.louis.springframework.context.support.ClassPathXmlApplicationContext;
import org.example.common.MyBeanFactoryPostProcessor;
import org.example.common.MyBeanPostProcessor;
import org.junit.Test;

/**
 * @date : 2021/8/16
 */
public class ApiTest {

    @Test
    public void testBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();


        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring-simple.xml");

        MyBeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);

        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

        UserService userService_singleton = (UserService) beanFactory.getBean("userService");
        userService_singleton.queryUserInfo();
    }

    @Test
    public void testXml(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }
}
