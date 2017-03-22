package com.vishesh.tpc_stud.auth.views;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.auth.presenters.LoginPresenter;
import com.vishesh.tpc_stud.core.views.BaseFragment;
import com.vishesh.tpc_stud.dashboard.views.DashboardFragment;

import javax.inject.Inject;

import butterknife.OnClick;

public class LoginFragment
        extends BaseFragment
        implements LoginPresenter.LoginView {

    @Inject
    LoginPresenter loginPresenter;

    private static final int ACCOUNT_KIT_REQUEST_CODE = 100;

    public LoginFragment() {
        setRetainInstance(true);
    }

    public static Intent createLoginIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginPresenter.setView(this);
    }

    @Override
    public void injectDependencies() {
        getDependencyInjector()
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @OnClick(R.id.button_login)
    void onEmailLoginClicked() {
        loginPresenter.onEmailLoginClicked();
    }

    @Override
    public void openDashboard() {
        Intent dashboardIntent = DashboardFragment.createIntent(getActivity());
        startActivity(dashboardIntent);
        finish();
    }

    @Override
    public void startLoginProcess(AccountKitConfiguration accountKitConfiguration) {
        final Intent accountKitIntent = new Intent(getActivity(), AccountKitActivity.class);
        accountKitIntent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, accountKitConfiguration);

        startActivityForResult(accountKitIntent, ACCOUNT_KIT_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case ACCOUNT_KIT_REQUEST_CODE:
                    AccountKitLoginResult accountKitLoginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
                    loginPresenter.onEmailLoginResultReceived(accountKitLoginResult);
                    break;
            }
        }
    }
}
