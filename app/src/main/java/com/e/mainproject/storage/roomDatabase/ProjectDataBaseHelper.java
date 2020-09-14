package com.e.mainproject.storage.roomDatabase;


import com.e.mainproject.storage.roomDatabase.userDB.UserDbCallback;
import com.e.mainproject.storage.roomDatabase.userDB.UserEntity;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.io;

public class ProjectDataBaseHelper {

    ProjectDao projectDao;

    @Inject
    public ProjectDataBaseHelper(ProjectDao projectDao){
        this.projectDao = projectDao;
    }

    public void insertUserData(UserEntity userEntity, UserDbCallback callback){
        Completable.fromAction(() -> projectDao.insertUserEntity(userEntity))
        .observeOn(mainThread())
        .subscribeOn(io())
        .subscribe(
                new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onComplete() {
                        callback.insertUserData(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.insertUserData(false);
                    }
                }
        );

    }

    public void updateUserEntity(UserEntity userEntity, UserDbCallback callback){
        Completable.fromAction(() -> projectDao.updateUserEntity(userEntity)).observeOn(mainThread()).subscribeOn(io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }
            @Override
            public void onComplete() {
                callback.updatedUserData(true);
            }
            @Override
            public void onError(Throwable e) {
                callback.updatedUserData(false);
            }
        });
    }

    public void deleteUserEntity(int userId, UserDbCallback callback){
        Completable.fromAction(() -> projectDao.deleteUserEntity(userId)).observeOn(mainThread()).subscribeOn(io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onComplete() {
                        callback.deleteUserDataItem(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.deleteUserDataItem(false);

                    }
                });
    }

    public void getUserEntityList(UserDbCallback callback){
        projectDao.getUserEntityList().observeOn(mainThread()).subscribeOn(io()).subscribe(
                new DisposableSingleObserver<List<UserEntity>>() {
                    @Override
                    public void onSuccess(List<UserEntity> userEntities) {
                        callback.onUserData(userEntities);
                    }
                    @Override
                    public void onError(Throwable e) {
                        callback.onError();
                    }
                }
        );
    }

    public void getEmailId(UserDbCallback callback){
        projectDao.getEmailIdList().observeOn(mainThread()).subscribeOn(io()).subscribe(
                new DisposableSingleObserver<List<String>>() {
                    @Override
                    public void onSuccess(List<String> emailIdList) {
                        callback.onEmailIdList(emailIdList);
                    }
                    @Override
                    public void onError(Throwable e) {
                        callback.onError();
                    }
                }
        );
    }

    public void getUserEntityById(int userId, UserDbCallback callback){
        projectDao.getUserEntityById(userId).observeOn(mainThread()).subscribeOn(io()).subscribe(
                new DisposableSingleObserver<UserEntity>() {
                    @Override
                    public void onSuccess(UserEntity userEntity) {
                        callback.onUserDataById(userEntity);
                    }
                    @Override
                    public void onError(Throwable e) {
                        callback.onError();
                    }
                }
        );
    }
}
