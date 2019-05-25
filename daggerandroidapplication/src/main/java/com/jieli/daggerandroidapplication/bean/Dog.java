package com.jieli.daggerandroidapplication.bean;

import javax.inject.Inject;

public class Dog {

    private String name;



    public Dog(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }
}
