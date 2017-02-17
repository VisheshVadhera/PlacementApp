package com.vishesh.tpc_stud.core.views;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.vishesh.tpc_stud.R;

import butterknife.BindView;

/**
 * Created by vishesh on 17/2/17.
 */
public abstract class BaseFragment extends Fragment {

    @BindView(R.id.view_loader)
    SpinKitView spinKitView;

    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
    }

    public void showLoader() {
        spinKitView.setVisibility(View.VISIBLE);
    }

    public void hideLoader() {
        spinKitView.setVisibility(View.GONE);
    }

    public abstract void injectDependencies();
}
