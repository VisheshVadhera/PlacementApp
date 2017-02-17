package com.vishesh.tpc_stud.core.views;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.vishesh.tpc_stud.R;

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

    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT)
                .show();
    }

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

    protected abstract void injectDependencies();

    protected abstract int getLayoutId();
}
