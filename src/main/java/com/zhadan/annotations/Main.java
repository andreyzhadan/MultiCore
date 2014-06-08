package com.zhadan.annotations;

import java.lang.annotation.Annotation;

/**
 * Created by andrewzhadan on 5/1/14.
 */
public class Main {
    public static void main(String[] args) {
        Class aClass = AnnotatedClass.class;
        Annotation[] annotations = aClass.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof History) {
                History history = (History) annotation;
                for (Version version : history.versions()) {
                    System.out.println("version#" + version.version());
                    Date date = version.date();
                    System.out.println("    date " + date.day() + ":" + date.month() + ":" + date.year());
                    Author author = version.author();
                    System.out.println("    author " + author.name() + " " + author.title());
                    Author[] helpers = version.helpers();
                    if (helpers.length != 0) {
                        System.out.println("    list of helpers->");
                        for (Author helper : helpers) {
                            System.out.println("        one of helpers " + helper.name() + " " + helper.title());
                        }
                    }
                }
            }
        }
    }
}
