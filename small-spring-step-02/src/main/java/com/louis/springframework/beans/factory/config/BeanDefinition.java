package com.louis.springframework.beans.factory.config;

/**
 *
 * @date : 2021/8/16
 */
public class BeanDefinition {

    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }
}
