package org.example;

import com.louis.springframework.beans.BeansException;
import com.louis.springframework.beans.factory.ApplicationContextAware;
import com.louis.springframework.beans.factory.BeanClassLoaderAware;
import com.louis.springframework.beans.factory.BeanFactory;
import com.louis.springframework.beans.factory.BeanFactoryAware;
import com.louis.springframework.beans.factory.BeanNameAware;
import com.louis.springframework.beans.factory.DisposableBean;
import com.louis.springframework.beans.factory.InitializingBean;
import com.louis.springframework.context.ApplicationContext;

/**
 * @date : 2021/8/16
 */
public class UserService implements InitializingBean, DisposableBean, BeanNameAware, BeanClassLoaderAware, ApplicationContextAware, BeanFactoryAware {

    private ApplicationContext applicationContext;

    private BeanFactory beanFactory;

    private String uId;

    private String company;

    private String location;

    private UserDao userDao;

    public void queryUserInfo() {
        System.out.println("Query User Info: " + userDao.queryUserName(uId) + ": " + toString());
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserService{");
        sb.append("uId='").append(uId).append('\'');
        sb.append(", company='").append(company).append('\'');
        sb.append(", location='").append(location).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("invoke userService.destroy()");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("invoke userService.afterPropertiesSet()");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("ClassLoader: " + classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Bean Name is: " + name);
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
