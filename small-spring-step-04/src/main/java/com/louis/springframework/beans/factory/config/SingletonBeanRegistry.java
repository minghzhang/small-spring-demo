package com.louis.springframework.beans.factory.config;

/**
 * @author : landon
 * @date : 2021/8/16
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);
}
