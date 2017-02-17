package com.vishesh.tpc_stud.auth.presenters;

import com.facebook.accountkit.AccountKitLoginResult;

/**
 * Created by vishesh on 12/2/17.
 */
public class LoginPresenter {



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
}
