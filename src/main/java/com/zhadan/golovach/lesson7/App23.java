package com.zhadan.golovach.lesson7;

import java.lang.reflect.Field;

interface Xmler {

    public default String toXml() throws IllegalAccessException {
        String simpleName = this.getClass().getSimpleName();
        StringBuilder result = new StringBuilder("<" + simpleName + ">\n");
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field f : fields) {
            result.append("   <" + f.getName() + ">");
            result.append(f.get(this));
            result.append("</" + f.getName() + ">\n");
        }
        result.append("</" + simpleName + ">");
        return result.toString();
    }
}

/**
 * Created by andrewzhadan on 6/22/14.
 */
public class App23 implements Xmler {
    int x;
    int y;

    public App23(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static void main(String[] args) throws IllegalAccessException {
        System.out.println(new App23(10, 20).toXml());
    }
}
