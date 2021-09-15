package org.example;

import com.louis.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @date : 2021/9/15
 */
public class ProxyBeanFactory implements FactoryBean<UserDao> {

    @Override
    public UserDao getObject() throws Exception {
        InvocationHandler handler = ((proxy, method, args) -> {

            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("10001", "zhagnsan");
            hashMap.put("10002", "lisi");
            hashMap.put("10003", "wangwu");
            return "你被代理了 " + method.getName() + ":" + hashMap.get(args[0].toString());
        });
        return (UserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{UserDao.class}, handler);
    }

    @Override
    public Class<?> getObjectType() {
        return UserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
