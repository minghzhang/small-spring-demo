package org.example;

import com.louis.springframework.context.ApplicationListener;
import com.louis.springframework.context.event.ContextRefreshedEvent;

/**
 * @date : 2021/9/15
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("刷新事件：" + this.getClass().getName());
    }
}
