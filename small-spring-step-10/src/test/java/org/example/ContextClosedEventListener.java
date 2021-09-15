package org.example;

import com.louis.springframework.context.ApplicationListener;
import com.louis.springframework.context.event.ContextClosedEvent;

/**
 * @date : 2021/9/15
 */
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件：" + this.getClass().getName());
    }
}
