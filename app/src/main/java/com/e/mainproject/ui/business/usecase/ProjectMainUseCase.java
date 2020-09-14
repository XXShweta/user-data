package com.e.mainproject.ui.business.usecase;

import com.e.mainproject.storage.roomDatabase.ProjectDataBaseHelper;
import com.e.mainproject.storage.roomDatabase.userDB.UserDbCallback;
import com.e.mainproject.storage.roomDatabase.userDB.UserEntity;

import javax.inject.Inject;

public class ProjectMainUseCase {

    private ProjectDataBaseHelper projectDataBaseHelper;

    @Inject
    public ProjectMainUseCase(  ProjectDataBaseHelper projectDataBaseHelper){
        this.projectDataBaseHelper = projectDataBaseHelper;
    }

    public void insertUserData(UserEntity userEntity, UserDbCallback callback){
        projectDataBaseHelper.insertUserData(userEntity,callback);
    }

    public void updateUserData(UserEntity userEntity, UserDbCallback callback){
        projectDataBaseHelper.updateUserEntity(userEntity,callback);
    }

    public void deleteUserData(int userId, UserDbCallback callback){
        projectDataBaseHelper.deleteUserEntity(userId,callback);
    }

    public void getUserDataList(UserDbCallback callback){
        projectDataBaseHelper.getUserEntityList(callback);
    }

    public void getEmailIdList(UserDbCallback callback){
        projectDataBaseHelper.getEmailId(callback);
    }

    public void getUserDataById(int  userId, UserDbCallback callback){
        projectDataBaseHelper.getUserEntityById(userId,callback);
    }

}
