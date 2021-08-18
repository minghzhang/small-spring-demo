package com.louis.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.louis.springframework.beans.BeansException;
import com.louis.springframework.beans.PropertyValue;
import com.louis.springframework.beans.PropertyValues;
import com.louis.springframework.beans.factory.Aware;
import com.louis.springframework.beans.factory.BeanClassLoaderAware;
import com.louis.springframework.beans.factory.BeanFactoryAware;
import com.louis.springframework.beans.factory.BeanNameAware;
import com.louis.springframework.beans.factory.DisposableBean;
import com.louis.springframework.beans.factory.InitializingBean;
import com.louis.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.louis.springframework.beans.factory.config.BeanDefinition;
import com.louis.springframework.beans.factory.config.BeanPostProcessor;
import com.louis.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @date : 2021/8/16
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiateStrategy();

    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);
            //fill up property values
            applyPropertyValues(beanName, bean, beanDefinition);
            //执行Bean的初始化方法和BeanPostProcessor的前置和后置处理方法
            bean = initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed,beanName:" + beanName, e);
        }
        //注册实现了DisposableBean接口的Bean对象
        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);
        addSingleton(beanName, bean);
        return bean;
    }

    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
    }

    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        //invoke AwareMethods
        if (bean instanceof Aware) {
            if (bean instanceof BeanFactoryAware) {
                BeanFactoryAware.class.cast(bean).setBeanFactory(this);
            }
            if (bean instanceof BeanClassLoaderAware) {
                BeanClassLoaderAware.class.cast(bean).setBeanClassLoader(getBeanClassLoader());
            }
            if (bean instanceof BeanNameAware) {
                BeanNameAware.class.cast(bean).setBeanName(beanName);
            }
        }

        Object wrapperBean = applyBeanPostProcessorBeforeInitialization(bean, beanName);

        try {
            invokeInitMethods(beanName, wrapperBean, beanDefinition);
        } catch (Exception exception) {
            throw new BeansException("Invocation of init method of bean[" + beanName + "] failed", exception);
        }

        wrapperBean = applyBeanPostProcessorAfterInitialization(bean, beanName);

        return wrapperBean;
    }

    private void invokeInitMethods(String beanName, Object wrapperBean, BeanDefinition beanDefinition) throws Exception {
        //1、实现接口 InitializingBean
        if (wrapperBean instanceof InitializingBean) {
            ((InitializingBean) wrapperBean).afterPropertiesSet();
        }

        //2、配置init-method
        String initMethodName = beanDefinition.getInitMethodName();
        if (StrUtil.isNotEmpty(initMethodName)) {
            Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
            if (initMethod == null) {
                throw new BeansException("Could not find an init method name '" +
                        initMethodName + "' on bean with name '" + beanName + "'");
            }
            initMethod.invoke(wrapperBean);
        }
    }

    private void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String propertyName = propertyValue.getName();
                Object value = propertyValue.getValue();
                if (value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                BeanUtil.setFieldValue(bean, propertyName, value);
            }
        } catch (Exception ex) {
            throw new BeansException("Error setting property values:" + beanName);
        }
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String name, Object[] args) {
        Constructor constructorToUse = null;
        Class beanClass = beanDefinition.getBeanClass();
        Constructor[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor ctor : declaredConstructors) {
            if (args != null && ctor.getParameterTypes().length == args.length) {
                constructorToUse = ctor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, name, constructorToUse, args);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    @Override
    public Object applyBeanPostProcessorBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        List<BeanPostProcessor> beanPostProcessors = getBeanPostProcessors();
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            Object current = beanPostProcessor.postProcessBeforeInitialization(result, beanName);
            if (null == current) {
                return result;
            }
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        List<BeanPostProcessor> beanPostProcessors = getBeanPostProcessors();
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            Object current = beanPostProcessor.postProcessAfterInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }
}
