package com.zhadan.golovach.lesson8.optional;

import java.util.Optional;

/**
 * Created by andrewzhadan on 6/29/14.
 */
public class Address {
    private Optional<City> city;

    public Address(City city) {
        this.city = Optional.ofNullable(city);
    }

    public Optional<City> getCity() {
        return city;
    }

    public void setCity(Optional<City> city) {
        this.city = city;
    }
}
