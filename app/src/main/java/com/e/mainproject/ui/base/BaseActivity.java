package com.e.mainproject.ui.base;

import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity <T extends ViewDataBinding> extends DaggerAppCompatActivity {
    public T mViewDataBinding;

    @LayoutRes
    public abstract int getLayoutId();

    public abstract int getBindingVariable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
        performDataBinding();
    }

    private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this,getLayoutId());
        mViewDataBinding.setLifecycleOwner(this);
        mViewDataBinding.executePendingBindings();
    }

    private void performDependencyInjection() {
        AndroidInjection.inject(this);
    }

    public T getActivityDataBinding() {
        return mViewDataBinding;
    }

}
