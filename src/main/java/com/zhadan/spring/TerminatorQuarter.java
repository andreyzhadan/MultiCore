package com.zhadan.spring;

import com.zhadan.spring.annotaions.InjectRandomInt;
import com.zhadan.spring.annotaions.PostProxy;
import com.zhadan.spring.annotaions.Profiling;

import javax.annotation.PostConstruct;

/**
 * Created by andrewzhadan on 8/2/14.
 */
@Profiling
public class TerminatorQuarter implements Quarter {
    private String message;

    @InjectRandomInt(min = 1, max = 7)
    private int repeatTimes;

    public TerminatorQuarter() {
        System.out.println("Phase 1");
    }

    @PostConstruct
    public void init() {
        System.out.println("Phase 2 / repeated " + repeatTimes);
    }

    @Override
    @PostProxy
    public void sayHello() {
        System.out.println("Phase 3");
        for (int i = 0; i < repeatTimes; i++) {
            System.out.println(getMessage());
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        System.out.println("setMessage");
        this.message = message;
    }
}
