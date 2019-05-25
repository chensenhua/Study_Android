package com.jieli.daggerandroidapplication;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.DispatchingAndroidInjector;

import javax.inject.Inject;

public class MainApplication extends DaggerApplication
    implements Application.ActivityLifecycleCallbacks
      {




    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(this);


    }

          @Override
          protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
              return DaggerAppComponent.builder().create(this);
          }


          @Override
          public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
           activityInjector().inject(activity);

          }

          @Override
          public void onActivityStarted(Activity activity) {

          }

          @Override
          public void onActivityResumed(Activity activity) {

          }

          @Override
          public void onActivityPaused(Activity activity) {

          }

          @Override
          public void onActivityStopped(Activity activity) {

          }

          @Override
          public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

          }

          @Override
          public void onActivityDestroyed(Activity activity) {

          }
      }
