package com.e.mainproject.di.module;

import com.e.mainproject.ui.fragment.ProjectDetailFrgament;
import com.e.mainproject.ui.fragment.ProjectMainFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract ProjectMainFragment provideProjectMainFragment();

    @ContributesAndroidInjector
    abstract ProjectDetailFrgament provideProjectDetailFrgament();

}
