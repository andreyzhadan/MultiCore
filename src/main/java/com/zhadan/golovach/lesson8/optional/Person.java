package com.zhadan.golovach.lesson8.optional;

import java.util.Optional;

/**
 * Created by andrewzhadan on 6/29/14.
 */
public class Person {
    private Optional<Address> address;

    public Person(Address address) {
        this.address = Optional.ofNullable(address);
    }

    public Optional<Address> getAddress() {
        return address;
    }

    public void setAddress(Optional<Address> address) {
        this.address = address;
    }
}
