package com.louis.springframework.context;

/**
 * @date : 2021/9/15
 */
public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);
}
