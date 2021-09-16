package com.louis.springframework.beans.factory;

/**
 *
 * @date : 2021/8/18
 */
public interface BeanClassLoaderAware extends Aware {

    void setBeanClassLoader(ClassLoader classLoader);
}
