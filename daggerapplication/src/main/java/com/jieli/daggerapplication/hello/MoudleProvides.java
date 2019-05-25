package com.jieli.daggerapplication.hello;

import dagger.Module;
import dagger.Provides;


import javax.inject.Singleton;


@Module
public class MoudleProvides {

    @Provides
    @ActivityScope
    Flower provideFlower() {
        return new Flower();
    }

    @Provides
    @ActivityScope
    String provideString() {
        return "test";
    }
}
