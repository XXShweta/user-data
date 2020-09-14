package com.e.mainproject.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.e.mainproject.R;
import com.e.mainproject.storage.roomDatabase.userDB.UserDbCallback;
import com.e.mainproject.storage.roomDatabase.userDB.UserEntity;
import com.e.mainproject.ui.base.BaseViewModel;
import com.e.mainproject.ui.business.usecase.ProjectMainUseCase;
import com.e.mainproject.ui.navigator.ProjectDetailNavigator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ProjectDetailViewModel extends BaseViewModel<ProjectDetailNavigator> implements UserDbCallback {

    public String userName = "";
    public String userEmail = "";
    public int userId=-1;
    private ProjectMainUseCase projectMainUseCase;
    private ArrayList<String> emailIds = new ArrayList<>();
    public MutableLiveData<Boolean> showError =new  MutableLiveData<>();

    @Inject
    ProjectDetailViewModel(ProjectMainUseCase projectMainUseCase){
        this.projectMainUseCase = projectMainUseCase;
        showError.setValue(false);
    }

    public void getUserData(){
        projectMainUseCase.getUserDataById(userId,this);
    }

    public void onSubmitButtonClicked(){
        if(validateEmailAndName()) {
            checkEmailIdIsAvailableInDB();
            showError.setValue(false);
        }else {
            showError.setValue(true);
        }
    }

    private void updateDb(){
        if (userId != -1) {
            UserEntity userEntity = new UserEntity(userId, userEmail, userName);
            projectMainUseCase.updateUserData(userEntity, this);
        }else {
            insertUserDataToDB();
        }
    }

    private Boolean validateEmailAndName() {
        if(userName.trim().length()>2 && userEmail.contains("@") && userEmail.contains(".com") && userEmail.trim().length()>4){
            return true;
        }
        return false;
    }

    private void checkEmailIdIsAvailableInDB() {
        projectMainUseCase.getEmailIdList(this);
    }

    private void insertUserDataToDB() {
        UserEntity userEntity = new UserEntity(0,userEmail,userName);
        projectMainUseCase.insertUserData(userEntity,this);
    }

    public void onNameTextChange(CharSequence s, int start, int before, int count){
        userName = s.toString();
    }

    public void onEmailTextChange(CharSequence s, int start, int before, int count){
        userEmail = s.toString();
    }

    @Override
    public void insertUserData(Boolean inserted) {
        if(inserted) {
            getViewNavigator().onSubmitButtonClick();
        }else {
            getViewNavigator().onError(R.string.error_message);
        }
    }

    @Override
    public void updatedUserData(Boolean updated) {
        if(updated) {
            getViewNavigator().onSubmitButtonClick();
        }else {
            getViewNavigator().onError(R.string.error_message);
        }
    }

    @Override
    public void deleteUserDataItem(Boolean deleted) {

    }

    @Override
    public void onUserData(List<UserEntity> userEntityArrayList) {

    }

    @Override
    public void onUserDataById(UserEntity userEntity) {
        if(userEntity != null) {
            userName = userEntity.userName;
            userEmail = userEntity.userEmailId;
            getViewNavigator().populateUi();
        }else {
            getViewNavigator().onError(R.string.error_message);
        }
    }

    @Override
    public void onError() {
        getViewNavigator().onError(R.string.error_message);
    }

    @Override
    public void onEmailIdList(List<String> emailIdList) {
        if(emailIdList!= null && emailIdList.size()>0){
            emailIds.addAll(emailIdList);
            if(emailIds.contains(userEmail)){
                getViewNavigator().onError(R.string.email_present);
            }else {
                updateDb();
            }
        }else {
            updateDb();
        }
    }
}
