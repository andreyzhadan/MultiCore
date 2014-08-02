package com.zhadan.golovach.lesson8.optional;

import java.util.HashMap;
import java.util.Optional;

/**
 * Created by andrewzhadan on 6/29/14.
 */
public class PersonMap<T, U> extends HashMap<T, U> {
    public Optional<U> find(T key) {
        return Optional.ofNullable(super.get(key));
    }
}