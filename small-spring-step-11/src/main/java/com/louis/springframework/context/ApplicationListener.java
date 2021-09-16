package com.louis.springframework.context;

import java.util.EventListener;

/**
 * @date : 2021/9/15
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    void onApplicationEvent(E event);
}
