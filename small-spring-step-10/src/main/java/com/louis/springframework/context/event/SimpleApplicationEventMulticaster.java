package com.louis.springframework.context.event;

import com.louis.springframework.beans.factory.BeanFactory;
import com.louis.springframework.context.AbstractApplicationEventMulticaster;
import com.louis.springframework.context.ApplicationEvent;
import com.louis.springframework.context.ApplicationListener;

import java.util.Collection;

/**
 * @date : 2021/9/15
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        Collection<ApplicationListener> applicationListeners = getApplicationListeners(event);
        if (applicationListeners != null && applicationListeners.size() > 0) {
            for (ApplicationListener listener : applicationListeners) {
                listener.onApplicationEvent(event);
            }
        }
    }
}
