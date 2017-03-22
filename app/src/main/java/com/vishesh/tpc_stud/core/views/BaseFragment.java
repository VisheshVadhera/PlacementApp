package com.vishesh.tpc_stud.core.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.core.dagger.TpcStudAppComponent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by vishesh on 17/2/17.
 */
public abstract class BaseFragment extends Fragment {

    @BindView(R.id.layout_loader)
    RelativeLayout relativeLayoutLoader;

    private Unbinder unbinder;
    private Snackbar snackbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        snackbar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT);
    }

    public void showMessage(String message) {
        snackbar.setText(message);
        snackbar.show();
    }

    public void showLoader() {
        relativeLayoutLoader.setVisibility(View.VISIBLE);
    }

    public void hideLoader() {
        relativeLayoutLoader.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected void finish() {
        getActivity().finish();
    }

    protected TpcStudAppComponent getDependencyInjector() {
        return ((BaseActivity) getActivity()).getApplicationComponent();
    }

    protected abstract void injectDependencies();

    protected abstract int getLayoutId();
}
