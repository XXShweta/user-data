package com.e.mainproject.di.component;


import android.app.Application;
import com.e.mainproject.ProjectApplication;
import com.e.mainproject.di.builder.ActivityBuilder;
import com.e.mainproject.di.module.AppModule;
import com.e.mainproject.di.module.ViewModelModule;
import javax.inject.Singleton;
import dagger.BindsInstance;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.Component;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,
        ActivityBuilder.class, ViewModelModule.class, AppModule.class})
public interface AppComponent extends AndroidInjector<ProjectApplication> {

    @Component.Builder
    interface Builder{

        @BindsInstance
         Builder application(Application application);

         AppComponent build();

    }
}
