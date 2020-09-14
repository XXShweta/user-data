package com.e.mainproject.ui.viewmodel;

import com.e.mainproject.ui.business.usecase.ProjectMainUseCase;
import com.e.mainproject.ui.fragment.ProjectMainFragment;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectMainViewModelTest {

    @Mock
    private ProjectMainFragment mFragment;

    private ProjectMainViewModel mViewModel;

    @Mock
    private ProjectMainUseCase projectMainUseCase;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mViewModel = new ProjectMainViewModel(projectMainUseCase);
        mViewModel.setNavigator(mFragment);
    }

    @Test
    public void getViewNavigator_returnsNull() {
        assertThat(mViewModel.getViewNavigator()).isNotNull();
    }



}