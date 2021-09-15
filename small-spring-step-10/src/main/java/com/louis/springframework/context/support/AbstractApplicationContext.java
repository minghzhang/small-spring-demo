package com.louis.springframework.context.support;

import com.louis.springframework.beans.BeansException;
import com.louis.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.louis.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.louis.springframework.beans.factory.config.BeanPostProcessor;
import com.louis.springframework.context.ApplicationEvent;
import com.louis.springframework.context.ApplicationListener;
import com.louis.springframework.context.ConfigurableApplicationContext;
import com.louis.springframework.context.event.ApplicationEventMulticaster;
import com.louis.springframework.context.event.ContextClosedEvent;
import com.louis.springframework.context.event.ContextRefreshedEvent;
import com.louis.springframework.context.event.SimpleApplicationEventMulticaster;
import com.louis.springframework.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

/**
 * @date : 2021/8/17
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    @Override
    public void refresh() throws BeansException {
        //1、create BeanFactory, and load BeanDefinitions
        refreshBeanFactory();

        //2. get BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        //3. add ApplicationContextAwareProcessor,
        // let these class which inherited ApplicationContextAware could be aware of ApplicationContext belongs to.
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        //4.before bean instantiates, invoke BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);

        //5.need register BeanPostProcessor before other bean's instantiation
        registerBeanPostProcessors(beanFactory);

        //6.init application event multicaster
        initApplicationEventMulticaster();

        //7.register listeners
        registerListeners();

        //8.instantiate singleton bean ahead of time.
        beanFactory.preInstantiateSingletons();

        //9. finish refresh
        finishRefresh();
    }

    private void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }


    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }


    private void registerListeners() {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener applicationListener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(applicationListener);
        }
    }


    private void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }

    }

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    protected abstract void refreshBeanFactory() throws BeansException;

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) {
        return getBeanFactory().getBean(name, requiredType);
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        //发布容器关闭事件
        publishEvent(new ContextClosedEvent(this));

        getBeanFactory().destroySingletons();
    }
}
