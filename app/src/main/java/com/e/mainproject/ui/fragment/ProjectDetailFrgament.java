package com.e.mainproject.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import com.e.mainproject.BR;
import com.e.mainproject.R;
import com.e.mainproject.databinding.DetailProjectFragmentBinding;
import com.e.mainproject.di.factory.DaggerViewModelFactory;
import com.e.mainproject.ui.base.BaseFragment;
import com.e.mainproject.ui.navigator.ProjectDetailNavigator;
import com.e.mainproject.ui.viewmodel.ProjectDetailViewModel;
import javax.inject.Inject;
import static androidx.navigation.fragment.FragmentKt.findNavController;

public class ProjectDetailFrgament extends BaseFragment<DetailProjectFragmentBinding, ProjectDetailViewModel> implements
        ProjectDetailNavigator {

    @Inject
    DaggerViewModelFactory providerFactory;
    private ProjectDetailViewModel mViewModel;

    @Override
    public int getBindingVariable() {
        return BR.projectDetailViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.detail_project_fragment;
    }

    @Override
    public ProjectDetailViewModel getViewModel() {
        mViewModel = new ViewModelProvider(this,providerFactory).get(ProjectDetailViewModel.class);
        mViewModel.setNavigator(this);
        return mViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            mViewModel.userId =ProjectDetailFrgamentArgs.fromBundle(getArguments()).getUserId();
          if(mViewModel.userId != -1)  {
              mViewModel.getUserData();
          }
        }
    }

    @Override
    public void onSubmitButtonClick() {
        findNavController(this).navigateUp();
    }

    @Override
    public void populateUi() {
        mViewDataBinding.txNameDetail.setText(mViewModel.userName);
        mViewDataBinding.txEmailDetail.setText(mViewModel.userEmail);
        mViewDataBinding.txSubmitDetail.setText(getString(R.string.update_button));
    }

    @Override
    public void onError(int error) {
        String errorMessage = getString(error);
        Toast.makeText(getContext(),errorMessage,Toast.LENGTH_LONG).show();
    }
}
