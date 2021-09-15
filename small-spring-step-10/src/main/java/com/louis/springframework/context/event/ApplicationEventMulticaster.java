package com.louis.springframework.context.event;

import com.louis.springframework.context.ApplicationEvent;
import com.louis.springframework.context.ApplicationListener;

/**
 * @date : 2021/9/15
 */
public interface ApplicationEventMulticaster {

    /**
     * add a listener to be notified of all events
     *
     * @param listener
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * remove a listener from the notification list
     *
     * @param listener
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * multicast the give application event to appropriate listeners
     *
     * @param event
     */
    void multicastEvent(ApplicationEvent event);
}
