package com.e.mainproject.storage.roomDatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.e.mainproject.storage.roomDatabase.userDB.UserEntity;
import java.util.List;
import io.reactivex.Single;

@Dao
public interface ProjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUserEntity(UserEntity userEntity);

    @Query("SELECT * FROM userDataTable")
    Single<List<UserEntity>> getUserEntityList();

    @Query("SELECT * FROM userDataTable Where userId =:id")
    Single<UserEntity> getUserEntityById(int id);

    @Query("DELETE FROM userDataTable Where userId =:id")
    void deleteUserEntity(int id);

    @Update
    void updateUserEntity(UserEntity userEntity);

    @Query("SELECT userEmailId FROM userDataTable")
    Single<List<String>> getEmailIdList();

}
