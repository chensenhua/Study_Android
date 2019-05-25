package com.jieli.daggerandroidapplication;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivitMoudle {

    @ContributesAndroidInjector
    abstract MainActivity provideMainActivity();

}
