package com.zhadan.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by andrewzhadan on 5/1/14.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Date {
    int day();

    int month();

    int year();
}
