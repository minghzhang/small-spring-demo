package com.louis.springframework.utils;

/**
 * @date : 2021/8/17
 */
public class ClassUtils {

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader contextClassLoader = null;
        try {
            contextClassLoader = Thread.currentThread().getContextClassLoader();
        } catch (Exception ex) {

        }
        //No thread context class loader -> use class loader of this class.
        if (contextClassLoader == null) {
            contextClassLoader = ClassUtils.class.getClassLoader();
        }
        return contextClassLoader;
    }

    public static boolean isCglibProxyClass(Class<?> clazz) {
        return clazz != null && isCglibProxyClassName(clazz.getName());
    }

    public static boolean isCglibProxyClassName(String className) {
        return className != null && className.contains("$$");
    }
}
