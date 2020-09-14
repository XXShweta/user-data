package com.e.mainproject.storage.roomDatabase.userDB;

import java.util.List;

public interface UserDbCallback {
    void insertUserData(Boolean inserted);
    void updatedUserData(Boolean updated);
    void deleteUserDataItem(Boolean deleted);
    void onUserData(List<UserEntity> userEntityArrayList);
    void onUserDataById(UserEntity userEntity);
    void onEmailIdList(List<String> emailIdList);
    void onError();
}
