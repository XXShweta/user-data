package com.e.mainproject.ui.activity;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;
import com.e.mainproject.R;
import com.e.mainproject.databinding.ActivityMainBinding;
import com.e.mainproject.ui.base.BaseActivity;

import java.util.HashSet;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private NavController navController;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();
    }

    private void initializeView() {
        navController = Navigation.findNavController(this, R.id.main_fragment);
        NavigationUI.setupActionBarWithNavController(this,navController);
        setupNavigation();
    }

    private void setupNavigation() {
        HashSet<Integer> topLevelDestination = new HashSet<Integer>();
        topLevelDestination.add(R.id.projectMainFrgament);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, (DrawerLayout) null);
    }
}
