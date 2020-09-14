package com.e.mainproject.di.module;

import android.content.Context;

import androidx.room.Room;

import com.e.mainproject.BuildConfig;
import com.e.mainproject.storage.roomDatabase.ProjectDao;
import com.e.mainproject.storage.roomDatabase.ProjectDataBase;
import com.e.mainproject.storage.roomDatabase.ProjectDataBaseHelper;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class DBModule {

    @Singleton
    @Provides
    public ProjectDataBase provideProjectDatabase(Context context)  {
        return Room.databaseBuilder(context, ProjectDataBase.class, BuildConfig.APPLICATION_ID)
            .fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }

    @Singleton
    @Provides
    public ProjectDao provideProjectDao(ProjectDataBase myDatabase) {
        return myDatabase.getProjectDao();
    }

    @Singleton
    @Provides
    public ProjectDataBaseHelper provideDatabaseHelper(ProjectDao projectDao) {
        return new ProjectDataBaseHelper(projectDao);
    }
}
