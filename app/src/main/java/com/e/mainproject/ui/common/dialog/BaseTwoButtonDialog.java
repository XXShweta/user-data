package com.e.mainproject.ui.common.dialog;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.e.mainproject.R;
import com.e.mainproject.databinding.BaseTwoButtonPopUpBinding;

public class BaseTwoButtonDialog extends DialogFragment {

    private static DialogCallback dialogCallback;
    private static @StringRes int mTitleStr;
    private static @StringRes int mActionButton;
    private static @StringRes int mActionButton2;

    public static BaseTwoButtonDialog newInstance(DialogCallback dialogCallback,@StringRes int titleStr, @StringRes int mActionButton, @StringRes int mActionButton2){
        BaseTwoButtonDialog baseTwoButtonDialog= new BaseTwoButtonDialog();
        baseTwoButtonDialog.dialogCallback = dialogCallback;
        baseTwoButtonDialog.mTitleStr = titleStr;
        baseTwoButtonDialog.mActionButton =mActionButton;
        baseTwoButtonDialog.mActionButton2 =mActionButton2;
        return baseTwoButtonDialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        setDialogSize();
    }

    private void setDialogSize() {
        try {
            getDialog().getWindow().setLayout((int) (getResources().getDisplayMetrics().widthPixels * 0.80),
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }catch (Exception e){
            Log.i("Error",e.getMessage());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        BaseTwoButtonPopUpBinding mDataBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.base_two_button_pop_up, container, true);
        mDataBinding.titletext.setText(mTitleStr);
        mDataBinding.firstButton.setText(mActionButton);
        mDataBinding.secondButton.setText(mActionButton2);

        mDataBinding.firstButton.setOnClickListener(v -> {
            dialogCallback.firstButtonClicked();
            dismiss();
        });

        mDataBinding.secondButton.setOnClickListener(v -> {
            dialogCallback.secondButtonClicked();
            dismiss();
        });
        return mDataBinding.getRoot();
    }
}
