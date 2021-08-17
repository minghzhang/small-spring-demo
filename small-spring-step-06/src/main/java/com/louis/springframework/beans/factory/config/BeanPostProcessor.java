package com.louis.springframework.beans.factory.config;

import com.louis.springframework.beans.BeansException;

/**
 * factory hook that allows for custom modification for new bean instances,
 * eg: checking for marker interface or wrapping them with proxies.
 *
 * @date : 2021/8/17
 */
public interface BeanPostProcessor {

    /**
     * 在Bean对象执行初始化方法之前，执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 在Bean对象执行初始化方法之后，执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
