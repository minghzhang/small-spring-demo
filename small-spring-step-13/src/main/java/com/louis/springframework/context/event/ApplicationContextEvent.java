package com.louis.springframework.context.event;

import com.louis.springframework.context.ApplicationContext;
import com.louis.springframework.context.ApplicationEvent;

/**
 * @date : 2021/9/15
 */
public class ApplicationContextEvent extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    /**
     * get the ApplicationContext that the event was raised for.
     *
     * @return
     */
    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
