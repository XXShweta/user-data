package com.e.mainproject.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.e.mainproject.di.factory.DaggerViewModelFactory;
import com.e.mainproject.di.key.ViewModelKey;
import com.e.mainproject.ui.viewmodel.ProjectDetailViewModel;
import com.e.mainproject.ui.viewmodel.ProjectMainViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(DaggerViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(ProjectMainViewModel.class)
    protected abstract ViewModel projectMainViewModel(ProjectMainViewModel projectMainViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProjectDetailViewModel.class)
    protected abstract ViewModel projectDetailViewModel(ProjectDetailViewModel projectDetailViewModel);


}
