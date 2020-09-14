package com.e.mainproject.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.e.mainproject.R;
import com.e.mainproject.storage.roomDatabase.userDB.UserDbCallback;
import com.e.mainproject.storage.roomDatabase.userDB.UserEntity;
import com.e.mainproject.ui.base.BaseViewModel;
import com.e.mainproject.ui.business.usecase.ProjectMainUseCase;
import com.e.mainproject.ui.navigator.ProjectMainNavigator;
import java.util.List;

import javax.inject.Inject;

public class ProjectMainViewModel extends BaseViewModel<ProjectMainNavigator>
        implements UserDbCallback {

    private ProjectMainUseCase projectMainUseCase;
    public MutableLiveData<List<UserEntity>> userEntityList = new MutableLiveData<>();
    public UserEntity selectedUserEntity;

    @Inject
    public ProjectMainViewModel(ProjectMainUseCase projectMainUseCase){
        this.projectMainUseCase = projectMainUseCase;
    }

     public void getUserDataFromDataBase(){
        projectMainUseCase.getUserDataList(this);
     }

     public void onAddUserButtonClick(){
        getViewNavigator().onAddNewUserClick();
     }

    public void onItemClick(UserEntity userEntity){
        selectedUserEntity = userEntity;
        getViewNavigator().showDialog();
    }

    public void deleteUserEntity(){
        projectMainUseCase.deleteUserData(selectedUserEntity.userId,this);
    }

     @Override
     public void insertUserData(Boolean inserted) { }

      @Override
      public void updatedUserData(Boolean updated) { }

      @Override
      public void deleteUserDataItem(Boolean deleted) {
        if(deleted){
            getViewNavigator().refreshFragment();
        }else {
            getViewNavigator().onError(R.string.error_message);
        }
      }

      @Override
      public void onUserData(List<UserEntity> userEntityArrayList) {
        if(userEntityArrayList != null){
            userEntityList.setValue(userEntityArrayList);
        }else {
            getViewNavigator().onError(R.string.no_user_data);
        }
      }

      @Override
      public void onUserDataById(UserEntity userEntity) { }

     @Override
     public void onError() {
        getViewNavigator().onError(R.string.error_message);
     }

     @Override
     public void onEmailIdList(List<String> emailIdList) { }

}
