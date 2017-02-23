package com.vishesh.tpc_stud.dashboard.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.auth.views.LoginFragment;
import com.vishesh.tpc_stud.core.ActivityComponent;
import com.vishesh.tpc_stud.core.views.BaseFragment;
import com.vishesh.tpc_stud.dashboard.adapters.SectionsPagerAdapter;
import com.vishesh.tpc_stud.dashboard.presenters.DashboardPresenter;

import javax.inject.Inject;

import butterknife.BindView;

public class DashboardFragment
        extends BaseFragment
        implements DashboardPresenter.DashboardView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.container)
    ViewPager viewPager;

    @Inject
    SectionsPagerAdapter sectionsPagerAdapter;

    @Inject
    DashboardPresenter dashboardPresenter;

    @Override
    protected void injectDependencies() {
        getDependencyInjector(ActivityComponent.class)
                .inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dashboard;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeView();

        dashboardPresenter.setView(this);
    }

    private void initializeView() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        sectionsPagerAdapter.addFragment(0, RecruitersFragment.newInstance());
        sectionsPagerAdapter.addFragment(1, ProfileFragment.newInstance());

        viewPager.setAdapter(sectionsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_dashboard, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                dashboardPresenter.onLogoutClicked();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void openLoginScreen() {
        startActivity(LoginFragment.createLoginIntent(getActivity()));
        finish();
    }
}
