package com.louis.springframework.context.support;

import com.louis.springframework.beans.BeansException;
import com.louis.springframework.beans.factory.ApplicationContextAware;
import com.louis.springframework.beans.factory.config.BeanPostProcessor;
import com.louis.springframework.context.ApplicationContext;

/**
 *
 * @date : 2021/8/18
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware) {
            ApplicationContextAware.class.cast(bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
