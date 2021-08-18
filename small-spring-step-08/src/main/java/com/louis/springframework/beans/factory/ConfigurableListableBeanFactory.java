package com.louis.springframework.beans.factory;

import com.louis.springframework.beans.BeansException;
import com.louis.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.louis.springframework.beans.factory.config.BeanDefinition;
import com.louis.springframework.beans.factory.config.BeanPostProcessor;
import com.louis.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * Configuration interface to be implemented by most listable bean factories.
 * In additon to {@link ConfigurableBeanFactory}, it provides facilities to analyze
 * and modify bean definitions, and to pre-instantiate singletons.
 *
 * @date : 2021/8/17
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
