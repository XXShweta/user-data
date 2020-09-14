package com.e.mainproject.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.e.mainproject.BR;
import com.e.mainproject.R;
import com.e.mainproject.databinding.MainProjectFragmentBinding;
import com.e.mainproject.di.factory.DaggerViewModelFactory;
import com.e.mainproject.ui.adapter.UserListAdapter;
import com.e.mainproject.ui.base.BaseFragment;
import com.e.mainproject.ui.common.dialog.BaseTwoButtonDialog;
import com.e.mainproject.ui.common.dialog.DialogCallback;
import com.e.mainproject.ui.navigator.ProjectMainNavigator;
import com.e.mainproject.ui.viewmodel.ProjectMainViewModel;
import java.util.ArrayList;
import javax.inject.Inject;
import static androidx.navigation.fragment.FragmentKt.findNavController;

public class ProjectMainFragment extends BaseFragment<MainProjectFragmentBinding, ProjectMainViewModel>
        implements ProjectMainNavigator, DialogCallback {

    @Inject
    DaggerViewModelFactory providerFactory;
    private ProjectMainViewModel mViewModel;
    private UserListAdapter userListAdapter;
    private BaseTwoButtonDialog baseTwoButtonDialog;

    @Override
    public int getBindingVariable() {
        return BR.projectMainViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.main_project_fragment;
    }

    @Override
    public ProjectMainViewModel getViewModel() {
        mViewModel = new ViewModelProvider(this,providerFactory).get(ProjectMainViewModel.class);
        mViewModel.setNavigator(this);
        return mViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeView();
        observeUserData();
        mViewModel.getUserDataFromDataBase();
    }

    private void initializeView(){
        baseTwoButtonDialog = BaseTwoButtonDialog.newInstance(this, R.string.title,R.string.delete_button,R.string.update_button);
        userListAdapter = new UserListAdapter(getContext(), new ArrayList<>(),mViewModel);
        mViewDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mViewDataBinding.recyclerView.setAdapter(userListAdapter);
    }

    private void observeUserData() {
        mViewModel.userEntityList.observe(getViewLifecycleOwner(), userEntities -> {
            if(userEntities != null) {
                if (userEntities.size() > 0) {
                    mViewDataBinding.noDataText.setVisibility(View.GONE);
                    mViewDataBinding.recyclerView.setVisibility(View.VISIBLE);
                } else {
                    mViewDataBinding.noDataText.setVisibility(View.VISIBLE);
                    mViewDataBinding.recyclerView.setVisibility(View.GONE);
                }
                userListAdapter.setListData(userEntities);
            }
        });
    }

    @Override
    public void showDialog() {
        baseTwoButtonDialog.show(mBaseActivity.getSupportFragmentManager(),"BaseTwoButtonDialog");
    }

    @Override
    public void onAddNewUserClick() {
        findNavController(this).navigate(R.id.action_projectMainFragment_to_projectDetailFrgament);
    }

    @Override
    public void firstButtonClicked() {
        mViewModel.deleteUserEntity();
    }

    @Override
    public void secondButtonClicked() {
        ProjectMainFragmentDirections.ActionProjectMainFragmentToProjectDetailFrgament action =
                ProjectMainFragmentDirections.actionProjectMainFragmentToProjectDetailFrgament();
        action.setUserId(mViewModel.selectedUserEntity.userId);
        findNavController(this).navigate(action);
    }

    @Override
    public void refreshFragment() {
        mViewModel.getUserDataFromDataBase();
    }

    @Override
    public void onError(int error) {
        String errorMessage = getString(error);
        Toast.makeText(getContext(),errorMessage,Toast.LENGTH_LONG).show();
    }
}
