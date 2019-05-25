package com.jieli.daggerandroidapplication;


import com.jieli.daggerandroidapplication.bean.Dog;
import dagger.Module;
import dagger.Provides;

@Module
public class BeanMoudle {

    @Provides
    Dog getDog(){
        return  new Dog("test");
    }
}
