package com.vishesh.tpc_stud.auth.presenters;

import com.facebook.accountkit.AccountKitLoginResult;
import com.vishesh.tpc_stud.core.presenters.BasePresenter;
import com.vishesh.tpc_stud.core.views.BaseView;

/**
 * Created by vishesh on 12/2/17.
 */
public class LoginPresenter implements BasePresenter{


    public void onEmailLoginResultReceived(AccountKitLoginResult accountKitLoginResult) {

        String message;

        if (accountKitLoginResult.getError() != null) {
            //Show error to the user
            message = accountKitLoginResult.getError().getErrorType().getMessage();

        } else if (accountKitLoginResult.wasCancelled()) {
            //Show cancelled to the user
            message = "Login Cancelled";

        } else if (accountKitLoginResult.getAuthorizationCode() != null) {


        }
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    public interface LoginView extends BaseView {

    }
}
