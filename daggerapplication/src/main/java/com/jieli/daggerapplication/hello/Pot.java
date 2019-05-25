package com.jieli.daggerapplication.hello;

import javax.inject.Inject;

public class Pot {
 
    private Rose rose;
 

    @Inject
    public Pot(Rose rose) {
        this.rose = rose;
    }
 
    public String show() {
        return rose.whisper();
    }
}

