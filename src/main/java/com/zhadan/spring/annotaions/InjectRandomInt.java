package com.zhadan.spring.annotaions;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by andrewzhadan on 8/2/14.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectRandomInt {
    int min();

    int max();
}
