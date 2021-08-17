package com.louis.springframework.context.support;

import com.louis.springframework.beans.BeansException;
import com.louis.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author : landon
 * @date : 2021/8/17
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();

        loadBeanDefinitions(beanFactory);

        this.beanFactory = beanFactory;
    }

    @Override
    public DefaultListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }
}
