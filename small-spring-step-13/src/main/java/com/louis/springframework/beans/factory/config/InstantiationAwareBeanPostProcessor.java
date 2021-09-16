package com.louis.springframework.beans.factory.config;

import com.louis.springframework.beans.BeansException;

/**
 * @date : 2021/9/16
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * apply this BeanPostProcessor <i>before the target bean gets instantiated</i>
     * The returned bean object may be a proxy to use instead of the target bean,
     * effectively suppressing default instantiation of the target bean.
     * <p>
     * 在Bean对象执行初始化方法之前，执行此方法
     *
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;
}
