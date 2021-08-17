package com.louis.springframework.beans.factory.support;

import com.louis.springframework.beans.factory.config.BeanDefinition;

/**
 *
 * @date : 2021/8/16
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
