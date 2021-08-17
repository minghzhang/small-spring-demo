package com.louis.springframework.beans.factory.config;

/**
 * @author : landon
 * @date : 2021/8/17
 */
public class BeanReference {

    private String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
