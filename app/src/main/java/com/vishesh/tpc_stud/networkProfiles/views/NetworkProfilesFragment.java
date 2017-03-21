package com.vishesh.tpc_stud.networkProfiles.views;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.core.helpers.Bus;
import com.vishesh.tpc_stud.core.views.BaseFragment;
import com.vishesh.tpc_stud.dashboard.models.Network;
import com.vishesh.tpc_stud.dashboard.models.NetworkProfile;
import com.vishesh.tpc_stud.networkProfiles.adapters.NetworkProfileItemAdapter;
import com.vishesh.tpc_stud.networkProfiles.busEvents.NetworkProfileItemClickedEvent;
import com.vishesh.tpc_stud.networkProfiles.presenters.NetworkProfilesPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.functions.Consumer;


public class NetworkProfilesFragment
        extends BaseFragment
        implements NetworkProfilesPresenter.NetworkProfilesView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view_network_profiles)
    RecyclerView recyclerViewNetworkProfiles;
    @BindView(R.id.fab_menu)
    FloatingActionsMenu floatingActionsMenu;

    @Inject
    NetworkProfilesPresenter networkProfilesPresenter;
    @Inject
    NetworkProfileItemAdapter networkProfileItemAdapter;
    @Inject
    Bus bus;
    private AlertDialog alertDialog;

    public static Intent createIntent(Context context) {
        return new Intent(context, NetworkProfilesActivity.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        setupToolbar();
        setupUrlDialog();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        networkProfilesPresenter.setNetworkProfilesView(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        networkProfilesPresenter.onStart();
        bus.asFlowable()
                .subscribe(new BusEventConsumer());
    }

    @Override
    public void showNetworkProfiles(List<NetworkProfile> networkProfiles) {
        networkProfileItemAdapter.setData(networkProfiles);
        recyclerViewNetworkProfiles.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewNetworkProfiles.setAdapter(networkProfileItemAdapter);
    }

    @Override
    public void openExternalLink(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(url));
        startActivity(browserIntent);
    }

    @Override
    public void allowGitHubProfileAddition() {
        FloatingActionButton fab = createFab(R.string.fab_github_title);
        floatingActionsMenu.addButton(fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                networkProfilesPresenter.onAddNetworkProfileClicked(Network.GITHUB);
            }
        });
    }

    @Override
    public void allowLinkedInProfileAddition() {
        FloatingActionButton fab = createFab(R.string.fab_linkedin_title);
        floatingActionsMenu.addButton(fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                networkProfilesPresenter.onAddNetworkProfileClicked(Network.LINKEDIN);
            }
        });
    }

    @Override
    public void allowOtherProfilesAddition() {
        FloatingActionButton fab = createFab(R.string.fab_other_title);
        floatingActionsMenu.addButton(fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                networkProfilesPresenter.onAddNetworkProfileClicked(Network.OTHER);
            }
        });
    }

    @Override
    public void showUpdatedNetworkProfiles(List<NetworkProfile> networkProfiles) {
        networkProfileItemAdapter.notifyItemInserted(networkProfiles.size() - 1);
    }

    @Override
    public void askForProfileUrl(final Network network) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_network_profile_url, null);
        final EditText editText = (EditText) view.findViewById(R.id.edit_text_url);

        alertDialog.setTitle(network.getNetworkName());
        alertDialog.setView(view);

        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,
                getString(R.string.network_profile_url_dialog_positive),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String userInput = editText.getText().toString();
                        networkProfilesPresenter
                                .onNewNetworkProfileSaveClicked(userInput, network);
                        alertDialog.hide();
                    }
                });

        alertDialog.show();
    }

    @Override
    protected void injectDependencies() {
        getDependencyInjector().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_network_profiles;
    }

    private void setupToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        final ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        IconicsDrawable iconicsDrawable = new IconicsDrawable(getContext())
                .icon(GoogleMaterial.Icon.gmd_arrow_back)
                .sizeDp(16)
                .color(ContextCompat.getColor(getContext(), android.R.color.white));

        if (actionBar != null) {
            actionBar.setTitle(R.string.title_activity_network_profiles);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(iconicsDrawable);
        }
    }

    private void setupUrlDialog() {
        alertDialog = new AlertDialog.Builder(getContext())
                .setMessage(R.string.network_profile_url_dialog_message)
                .create();

        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE,
                getString(R.string.network_profile_url_dialog_negative),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.hide();
                    }
                });
    }

    private FloatingActionButton createFab(@StringRes int titleRes) {
        FloatingActionButton actionButton = new FloatingActionButton(getContext());
        actionButton.setTitle(getString(titleRes));
        actionButton.setColorNormal(android.R.color.white);
        actionButton.setColorPressedResId(R.color.white_pressed);
        actionButton.setSize(FloatingActionButton.SIZE_NORMAL);
        return actionButton;
    }

    private class BusEventConsumer implements Consumer<Object> {

        @Override
        public void accept(Object event) throws Exception {
            if (event instanceof NetworkProfileItemClickedEvent) {
                networkProfilesPresenter.onNetworkProfileItemClicked(((NetworkProfileItemClickedEvent) event)
                        .getNetworkProfile());
            }
        }
    }
}