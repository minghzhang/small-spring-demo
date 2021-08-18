package com.louis.springframework.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.louis.springframework.beans.BeansException;
import com.louis.springframework.beans.factory.DisposableBean;
import com.louis.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 *
 * @date : 2021/8/17
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;
    private final String beanName;
    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        //1.实现接口DisposableBean
        if (bean instanceof DisposableBean) {
            DisposableBean.class.cast(bean).destroy();
        }

        //2、配置信息destroy-method,判断是为了避免二次执行销毁
        if (StrUtil.isNotEmpty(destroyMethodName)
                && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if (destroyMethod == null) {
                throw new BeansException("Couldn't find a destroy method named '" +
                        destroyMethod + "' on bean with name '" + beanName + "'");
            }
            destroyMethod.invoke(bean);
        }
    }
}
