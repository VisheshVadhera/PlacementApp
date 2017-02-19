package com.vishesh.tpc_stud.auth.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.widget.Button;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.core.views.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import hu.akarnokd.rxjava.interop.RxJavaInterop;
import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.subscribers.DisposableSubscriber;

public class UserNameActivity extends BaseActivity {


    @BindView(R.id.edit_text_first_name)
    TextInputEditText editTextFirstName;
    @BindView(R.id.edit_text_last_name)
    TextInputEditText editTextLastName;
    @BindView(R.id.button_continue)
    Button buttonContinue;


    private Unbinder unbinder;

    private DisposableSubscriber<Boolean> disposableSubscriber;
    private Flowable<CharSequence> firstNameChangeObservable;
    private Flowable<CharSequence> lastNameChangeObservable;


    public static Intent createIntent(Context context) {
        return new Intent(context, UserNameActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_name);
        unbinder = ButterKnife.bind(this);
        initObservables();
        combineObservables();
    }

    @OnClick(R.id.button_continue)
    public void onClick() {
        setResult(Activity.RESULT_OK,
                LoginFragment.createIntent(editTextFirstName.getText().toString(),
                                            editTextLastName.getText().toString()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        disposableSubscriber.dispose();
    }

    private void initObservables() {
        firstNameChangeObservable = RxJavaInterop.toV2Flowable(RxTextView
                .textChanges(editTextFirstName)
                .skip(1));
        lastNameChangeObservable = RxJavaInterop.toV2Flowable(RxTextView
                .textChanges(editTextLastName)
                .skip(1));
    }

    private void combineObservables() {
        disposableSubscriber = new DisposableSubscriber<Boolean>() {
            @Override
            public void onNext(Boolean formValid) {
                if (formValid) {
                    buttonContinue.setEnabled(true);
//                    buttonContinue.setBackgroundColor(ContextCompat.getColor(UserNameActivity.this, R.color.colorPrimary));
                } else {
                    buttonContinue.setEnabled(false);
//                    buttonContinue.setBackgroundColor(ContextCompat.getColor(UserNameActivity.this, R.color.gray));
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        Flowable.combineLatest(firstNameChangeObservable,
                lastNameChangeObservable,
                new BiFunction<CharSequence, CharSequence, Boolean>() {
                    @Override
                    public Boolean apply(@NonNull CharSequence firstName, @NonNull CharSequence lastName) throws Exception {
                        return !TextUtils.isEmpty(firstName)
                                && !TextUtils.isEmpty(lastName);
                    }
                })
                .subscribe(disposableSubscriber);
    }
}
