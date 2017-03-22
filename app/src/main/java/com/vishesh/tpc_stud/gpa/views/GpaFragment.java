package com.vishesh.tpc_stud.gpa.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.core.views.BaseFragment;
import com.vishesh.tpc_stud.gpa.adapters.GpaItemAdapter;
import com.vishesh.tpc_stud.gpa.models.Gpa;
import com.vishesh.tpc_stud.gpa.presenters.GpaPresenter;

import javax.inject.Inject;

import butterknife.BindView;

public class GpaFragment
        extends BaseFragment
        implements GpaPresenter.GpaView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view_gpa)
    RecyclerView recyclerViewGpa;

    private GpaItemAdapter gpaItemAdapter;

    @Inject
    GpaPresenter gpaPresenter;

    public static Intent createIntent(Context context) {
        return new Intent(context, GpaFragment.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        setupToolbar();
        gpaItemAdapter = new GpaItemAdapter(getContext());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gpaPresenter.setView(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        gpaPresenter.onStart();
    }

    private void setupToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        final ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        IconicsDrawable iconicsDrawable = new IconicsDrawable(getContext())
                .icon(GoogleMaterial.Icon.gmd_arrow_back)
                .sizeDp(16)
                .color(ContextCompat.getColor(getContext(), android.R.color.white));

        if (actionBar != null) {
            actionBar.setTitle(R.string.title_activity_gpa);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(iconicsDrawable);
        }
    }

    @Override
    protected void injectDependencies() {
        getDependencyInjector().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gpa;
    }

    @Override
    public void showGpa(Gpa gpa) {
        gpaItemAdapter.setData(gpa.getCpis());
        recyclerViewGpa.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewGpa.setAdapter(gpaItemAdapter);
    }
}
