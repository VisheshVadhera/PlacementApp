package com.vishesh.tpc_stud.auth.views;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.auth.presenters.LoginPresenter;
import com.vishesh.tpc_stud.core.TpcStudApplication;
import com.vishesh.tpc_stud.core.views.BaseFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by vishesh on 12/2/17.
 */
public class LoginFragment extends BaseFragment {

    private static final int ACCOUNT_KIT_REQUEST_CODE = 100;

    @Inject
    LoginPresenter loginPresenter;

    public LoginFragment() {
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void injectDependencies() {
        ((TpcStudApplication) getActivity().getApplication()).getAppComponent().inject(this);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (resultCode) {
                case ACCOUNT_KIT_REQUEST_CODE:
                    AccountKitLoginResult accountKitLoginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
                    loginPresenter.onEmailLoginResultReceived(accountKitLoginResult);
            }
        }
    }
}
