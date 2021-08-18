package com.louis.springframework.beans.factory.config;

import com.louis.springframework.beans.BeansException;
import com.louis.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * Allows for custom modification of an application context's bean definition,
 * adapting the bean property values of the context's underlying bean factory.
 *
 * @date : 2021/8/17
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有的BeanDefinition加载完成后，实例化Bean对象之前，提供修改BeanDefinition属性的机制
     *
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
