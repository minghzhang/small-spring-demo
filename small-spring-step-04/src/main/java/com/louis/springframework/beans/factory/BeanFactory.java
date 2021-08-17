package com.louis.springframework.beans.factory;

import com.louis.springframework.beans.BeansException;

/**
 *
 * @date : 2021/8/16
 */
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    Object getBean(String name, Object... args) throws BeansException;
}
