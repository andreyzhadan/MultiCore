package com.zhadan.golovach.lesson8.optional;

/**
 * Created by andrewzhadan on 6/29/14.
 */
public class Main {
    public static void main(String[] args) {

        Person p1 = new Person(null);
        Person p4 = new Person(new Address(new City("Kharkov")));

        PersonMap<String, Person> personMap = new PersonMap<>();
        personMap.put("Kharkov", p1);

        personMap.find("Kharkov")
                .flatMap(Person::getAddress)
                .flatMap(Address::getCity)
                .ifPresent(City::process);
    }
}
