package com.louis.springframework.beans.factory;

/**
 *
 * @date : 2021/8/17
 */
public interface InitializingBean {

    /**
     * Bean处理了属性填充后 调用
     */
    void afterPropertiesSet();
}
