package com.louis.springframework.context;

import com.louis.springframework.beans.factory.HierarchicalBeanFactory;
import com.louis.springframework.beans.factory.ListableBeanFactory;
import com.louis.springframework.core.io.ResourceLoader;

/**
 *
 * @date : 2021/8/17
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader,ApplicationEventPublisher {

}
