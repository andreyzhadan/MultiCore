package com.zhadan.golovach.lesson8.optional;

/**
 * Created by andrewzhadan on 6/29/14.
 */
public class City {
    private String city;

    public City(String city) {
        this.city = city;
    }

    public String getCity() {

        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void process() {
        System.out.println(city);
    }
}
