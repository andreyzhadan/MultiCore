package com.zhadan.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Created by andrewzhadan on 8/2/14.
 */
public class MainTest {
    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        while (true) {
            Thread.sleep(1000);
            System.out.println("NEXT ATTEMPT");
            context.getBean(Quarter.class).sayHello();
        }
    }
}
