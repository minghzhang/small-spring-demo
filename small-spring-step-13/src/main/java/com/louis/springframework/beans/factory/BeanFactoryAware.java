package com.louis.springframework.beans.factory;

import com.louis.springframework.beans.BeansException;

/**
 * @date : 2021/8/18
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
