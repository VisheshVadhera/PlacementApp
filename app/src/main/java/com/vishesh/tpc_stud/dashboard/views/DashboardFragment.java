package com.vishesh.tpc_stud.dashboard.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.jakewharton.rxbinding2.support.design.widget.RxTabLayout;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.auth.views.LoginFragment;
import com.vishesh.tpc_stud.auth.views.UserNameActivity;
import com.vishesh.tpc_stud.core.dagger.ActivityComponent;
import com.vishesh.tpc_stud.core.views.BaseFragment;
import com.vishesh.tpc_stud.dashboard.adapters.SectionsPagerAdapter;
import com.vishesh.tpc_stud.dashboard.presenters.DashboardPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class DashboardFragment
        extends BaseFragment
        implements DashboardPresenter.DashboardView {

    public static final int USER_NAME_REQUEST_CODE = 101;
    public static final String EXTRA_FIRST_NAME = "EXTRA_FIRST_NAME";
    public static final String EXTRA_LAST_NAME = "EXTRA_LAST_NAME";

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

    private Observable<Integer> tabPositionObservable;
    private Consumer<Integer> tabPositionConsumer;

    public static Intent createIntent(Context context) {
        return new Intent(context, DashboardActivity.class);
    }

    public static Intent createUserNameIntent(String firstName, String lastName) {
        Intent userNameIntent = new Intent();
        userNameIntent.putExtra(EXTRA_FIRST_NAME, firstName);
        userNameIntent.putExtra(EXTRA_LAST_NAME, lastName);
        return userNameIntent;
    }

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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dashboardPresenter.setView(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        dashboardPresenter.onStart();
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

    @Override
    public void takeUserName() {
        Intent userNameIntent = UserNameActivity.createIntent(getActivity());
        startActivityForResult(userNameIntent, USER_NAME_REQUEST_CODE);
    }

    @Override
    public void setupTabs() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        sectionsPagerAdapter.addFragment(0, RecruitersFragment.newInstance());
        sectionsPagerAdapter.addFragment(1, ProfileFragment.newInstance());

        viewPager.setAdapter(sectionsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        IconicsDrawable homeIcon = new IconicsDrawable(getContext())
                .sizeDp(20)
                .icon(MaterialDesignIconic.Icon.gmi_home);

        IconicsDrawable profileIcon = new IconicsDrawable(getContext())
                .sizeDp(20)
                .icon(MaterialDesignIconic.Icon.gmi_account_box);

        tabLayout.getTabAt(0).setIcon(homeIcon);
        tabLayout.getTabAt(1).setIcon(profileIcon);

        final ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        tabPositionObservable = RxTabLayout
                .selections(tabLayout)
                .map(new Function<TabLayout.Tab, Integer>() {
                    @Override
                    public Integer apply(TabLayout.Tab tab) throws Exception {
                        return tab.getPosition();
                    }
                });

        tabPositionConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer tabPosition) throws Exception {
                if (actionBar != null) {
                    if (tabPosition == 0) {
                        actionBar.setTitle(getString(R.string.dashboard_tab_recruiters_title));
                    } else {
                        actionBar.setTitle(getString(R.string.dashboard_tab_profile_title));
                    }
                }
            }
        };

        tabPositionObservable
                .subscribe(tabPositionConsumer);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case USER_NAME_REQUEST_CODE:
                    String firstName = data.getStringExtra(DashboardFragment.EXTRA_FIRST_NAME);
                    String lastName = data.getStringExtra(DashboardFragment.EXTRA_LAST_NAME);
                    dashboardPresenter.onUserNameReceived(firstName, lastName);
                    break;
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dashboard;
    }
}
