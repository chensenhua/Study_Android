package com.jieli.daggerandroidapplication;

import android.app.Application;
import dagger.Component;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

import javax.inject.Singleton;

@Singleton
@Component(modules = {AndroidInjectionModule.class,
        BeanMoudle.class,ActivitMoudle.class, AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<MainApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<MainApplication> {

    }

    void inject(MainApplication application);
}
