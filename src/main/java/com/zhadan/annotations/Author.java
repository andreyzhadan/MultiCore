package com.zhadan.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by andrewzhadan on 5/1/14.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Author {
    String name() default "azhadan";

    JobTitle title() default JobTitle.JUNIOR;
}
