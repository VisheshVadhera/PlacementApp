package com.vishesh.tpc_stud.auth.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.core.views.BaseActivity;

public class UserNameActivity extends BaseActivity {

    public static Intent createIntent(Context context) {
        return new Intent(context, UserNameActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_name);
    }
}
