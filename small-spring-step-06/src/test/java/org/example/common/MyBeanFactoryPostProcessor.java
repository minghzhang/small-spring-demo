package org.example.common;

import com.louis.springframework.beans.BeansException;
import com.louis.springframework.beans.PropertyValue;
import com.louis.springframework.beans.PropertyValues;
import com.louis.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.louis.springframework.beans.factory.config.BeanDefinition;
import com.louis.springframework.beans.factory.config.BeanFactoryPostProcessor;

/**
 *
 * @date : 2021/8/17
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company", "ByteDancing"));
    }
}
