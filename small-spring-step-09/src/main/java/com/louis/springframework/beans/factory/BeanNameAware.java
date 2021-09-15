package com.louis.springframework.beans.factory;

/**
 *
 * @date : 2021/8/18
 */
public interface BeanNameAware extends Aware {

    void setBeanName(String name);
}
