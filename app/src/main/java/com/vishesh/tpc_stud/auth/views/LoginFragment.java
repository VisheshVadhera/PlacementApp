package com.vishesh.tpc_stud.auth.views;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.auth.presenters.LoginPresenter;
import com.vishesh.tpc_stud.core.TpcStudApplication;
import com.vishesh.tpc_stud.core.views.BaseFragment;

import javax.inject.Inject;

import butterknife.OnClick;

/**
 * Created by vishesh on 12/2/17.
 */
public class LoginFragment
        extends BaseFragment
        implements LoginPresenter.LoginView {

    private static final int ACCOUNT_KIT_REQUEST_CODE = 100;
    private static final int USER_NAME_REQUEST_CODE = 101;

    private static final String EXTRA_FIRST_NAME = "EXTRA_FIRST_NAME";
    private static final String EXTRA_LAST_NAME = "EXTRA_LAST_NAME";

    @Inject
    LoginPresenter loginPresenter;

    public LoginFragment() {
        setRetainInstance(true);
    }

    public static Intent createIntent(String firstName, String lastName) {
        Intent userNameIntent = new Intent();
        userNameIntent.putExtra(EXTRA_FIRST_NAME, firstName);
        userNameIntent.putExtra(EXTRA_LAST_NAME, lastName);
        return userNameIntent;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginPresenter.setView(this);
    }

    @Override
    public void injectDependencies() {
        ((TpcStudApplication) getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @OnClick(R.id.button_login)
    void onEmailLoginClicked() {

        AccountKitConfiguration accountKitConfiguration =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(LoginType.EMAIL,
                        AccountKitActivity.ResponseType.CODE).build();

        final Intent accountKitIntent = new Intent(getActivity(), AccountKitActivity.class);
        accountKitIntent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, accountKitConfiguration);

        startActivityForResult(accountKitIntent, ACCOUNT_KIT_REQUEST_CODE);
    }

    @Override
    public void takeUserName() {
        Intent userNameIntent = UserNameActivity.createIntent(getActivity());
        startActivityForResult(userNameIntent, USER_NAME_REQUEST_CODE);
    }

    @Override
    public void openDashboard() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case ACCOUNT_KIT_REQUEST_CODE:
                    AccountKitLoginResult accountKitLoginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
                    loginPresenter.onEmailLoginResultReceived(accountKitLoginResult);
                    break;
                case USER_NAME_REQUEST_CODE:
                    String firstName = data.getStringExtra(EXTRA_FIRST_NAME);
                    String lastName = data.getStringExtra(EXTRA_LAST_NAME);
                    loginPresenter.onUserNameReceived(firstName, lastName);
                    break;
            }
        }
    }
}
