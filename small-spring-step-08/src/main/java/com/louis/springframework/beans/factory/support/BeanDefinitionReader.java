package com.louis.springframework.beans.factory.support;

import com.louis.springframework.beans.BeansException;
import com.louis.springframework.core.io.Resource;
import com.louis.springframework.core.io.ResourceLoader;

/**
 * @date : 2021/8/17
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String... location) throws BeansException;
}
