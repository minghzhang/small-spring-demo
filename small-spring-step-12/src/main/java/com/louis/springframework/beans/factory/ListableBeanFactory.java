package com.louis.springframework.beans.factory;

import com.louis.springframework.beans.BeansException;

import java.util.Map;

/**
 *
 * @date : 2021/8/17
 */
public interface ListableBeanFactory extends BeanFactory {

    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    String[] getBeanDefinitionNames();
}
