package com.louis.springframework;

/**
 * @author : landon
 * @date : 2021/8/16
 */
public class BeanDefinition {

    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }
}
