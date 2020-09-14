package com.e.mainproject.storage.roomDatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.e.mainproject.storage.roomDatabase.userDB.UserEntity;

@Database(entities = {UserEntity.class},exportSchema = false, version = ProjectDataBase.VERSION)
public abstract class ProjectDataBase extends RoomDatabase {

    public static final int VERSION =1;

    abstract public ProjectDao getProjectDao();
}
