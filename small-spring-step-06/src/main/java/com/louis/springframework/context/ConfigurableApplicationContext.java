package com.louis.springframework.context;

import com.louis.springframework.beans.BeansException;

/**
 *
 * @date : 2021/8/17
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    void refresh() throws BeansException;
}
