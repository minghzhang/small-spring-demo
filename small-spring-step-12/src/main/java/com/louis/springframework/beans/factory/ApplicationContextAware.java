package com.louis.springframework.beans.factory;

import com.louis.springframework.beans.BeansException;
import com.louis.springframework.context.ApplicationContext;

/**
 *
 * @date : 2021/8/18
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
