package com.louis.springframework.beans.factory.config;

import com.louis.springframework.beans.BeansException;
import com.louis.springframework.beans.factory.BeanFactory;

/**
 * Extension of the {@link BeanFactory} interface to be implemented by bean factories
 * that are capable of autowiring, provided that they want to expose this functionality
 * for existing instances.
 *
 * @date : 2021/8/17
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 执行BeanPostProcessor接口实现类的 postProcessBeforeInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    /**
     * 执行BeanPostProcessor接口实现类的 postProcessAfterInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorAfterInitialization(Object existingBean, String beanName) throws BeansException;
}
