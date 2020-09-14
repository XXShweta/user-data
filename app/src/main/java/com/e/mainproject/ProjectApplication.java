package com.e.mainproject;

import com.e.mainproject.di.component.AppComponent;
import com.e.mainproject.di.component.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class ProjectApplication extends DaggerApplication{

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }
}
