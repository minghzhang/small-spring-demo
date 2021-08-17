package com.louis.springframework.context.support;

import com.louis.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.louis.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author : landon
 * @date : 2021/8/17
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (configLocations != null) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
