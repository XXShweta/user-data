package com.e.mainproject.di.builder;

import com.e.mainproject.di.module.FragmentModule;
import com.e.mainproject.ui.activity.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {FragmentModule.class})
    abstract MainActivity bindActivity();
}
