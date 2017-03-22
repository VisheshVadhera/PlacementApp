package com.vishesh.tpc_stud.networkProfiles.views;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.vishesh.tpc_stud.R;

import butterknife.BindView;
import butterknife.ButterKnife;

class AddNetworkProfileDialog {

    @BindView(R.id.text_url_title)
    TextView textUrlTitle;
    @BindView(R.id.edit_text_url)
    EditText editTextUrl;
    @BindView(R.id.button_negative)
    Button buttonNegative;
    @BindView(R.id.button_positive)
    Button buttonPositive;

    private final Context context;

    private AlertDialog alertDialog;

    public AddNetworkProfileDialog(Context context) {
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_add_network_profile_url, null);

        ButterKnife.bind(this, view);

        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setView(view);

        alertDialog = builder.create();
    }

    public void setTitle(@StringRes int titleRes) {
        textUrlTitle.setText(context.getString(titleRes));
    }

    public String getUserInput() {
        return editTextUrl.getText().toString();
    }

    public void show() {
        alertDialog.show();
    }

    public void dismiss() {
        alertDialog.dismiss();
    }

    public void setPositiveButton(@StringRes int textRes, View.OnClickListener onClickListener) {
        buttonPositive.setText(context.getString(textRes));
        buttonPositive.setOnClickListener(onClickListener);
    }

    public void setNegativeButton(@StringRes int textRes, View.OnClickListener onClickListener) {
        buttonNegative.setText(context.getString(textRes));
        buttonNegative.setOnClickListener(onClickListener);
    }
}
