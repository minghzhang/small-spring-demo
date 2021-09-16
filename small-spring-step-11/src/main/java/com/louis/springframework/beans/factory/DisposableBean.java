package com.louis.springframework.beans.factory;

/**
 *
 * @date : 2021/8/17
 */
public interface DisposableBean {

    void destroy() throws Exception;
}
