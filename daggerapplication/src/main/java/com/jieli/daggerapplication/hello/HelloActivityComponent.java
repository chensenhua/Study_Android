package com.jieli.daggerapplication.hello;

import dagger.Component;

import javax.inject.Singleton;

@ActivityScope
@Component(modules = MoudleProvides.class)
public interface HelloActivityComponent {
    void inject(HelloActivity helloActivity);



    Rose getRose();


    @Component.Builder
    interface  Builder{
        HelloActivityComponent build();
    }
}
