package com.louis.springframework.core.io;

/**
 *
 * @date : 2021/8/17
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);
}
