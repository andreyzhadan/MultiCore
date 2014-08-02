package com.zhadan.spring.postprocessors;

import com.zhadan.spring.ProfilerController;
import com.zhadan.spring.annotaions.Profiling;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;

import static java.lang.reflect.Proxy.newProxyInstance;

/**
 * Created by azhadan on 8/2/14.
 */
public class ProfilingAnnotationPostProcessor implements BeanPostProcessor {
    private Map<String, Class> map = new HashMap<>();
    private ProfilerController controller = new ProfilerController();

    public ProfilingAnnotationPostProcessor() throws Exception {
        MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
        platformMBeanServer.registerMBean(controller, new ObjectName("profiling", "name", "controller"));
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization " + "ProfilingAnnotationPostProcessor");
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(Profiling.class)) {
            map.put(beanName, beanClass);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization " + "ProfilingAnnotationPostProcessor");
        Class beanClass = map.get(beanName);
        if (beanClass != null) {
            return newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), (proxy, method, args) -> {
                if (controller.isEnabled()) {
                    System.out.println("Profiling STARTS");
                    long before = System.nanoTime();
                    Object retVal = method.invoke(bean, args);
                    long after = System.nanoTime();
                    System.out.println("Takes " + (after - before));
                    System.out.println("Profiling ENDS ");
                    return retVal;
                } else {
                    return method.invoke(bean, args);
                }
            });
        }
        return bean;
    }
}
