package com.louis.springframework.beans;

/**
 * @author : landon
 * @date : 2021/8/16
 */
public class BeansException extends RuntimeException {

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
