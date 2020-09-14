package com.e.mainproject.storage.roomDatabase.userDB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "userDataTable")
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    public int userId;
    public String userEmailId;
    public String userName;

    public UserEntity(int userId, String userEmailId, String userName) {
        this.userId = userId;
        this.userEmailId = userEmailId;
        this.userName = userName;
    }
}
