package com.louis.springframework.beans.factory;

/**
 * @date : 2021/9/15
 */
public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();
}
