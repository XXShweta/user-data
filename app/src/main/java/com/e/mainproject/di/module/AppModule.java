package com.e.mainproject.di.module;

import android.app.Application;
import android.content.Context;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module(includes = { DBModule.class})
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application){
        return application;
    }
}
