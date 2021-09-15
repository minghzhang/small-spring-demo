package org.example;

import com.louis.springframework.beans.factory.BeanFactory;
import com.louis.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.louis.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Assert;
import org.junit.Test;

/**
 * @date : 2021/8/16
 */
public class ApiTest {


    @Test
    public void testEvent() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-simple.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 100000002324L, "Yes, it success !"));
        applicationContext.registerShutdownHook();
    }


    @Test
    public void testIsAssignFrom() {
        Assert.assertEquals(BeanFactory.class.isAssignableFrom(DefaultListableBeanFactory.class), true);
    }
}
