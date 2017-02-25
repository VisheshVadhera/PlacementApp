package com.vishesh.tpc_stud.dashboard.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.core.utils.UiUtils;
import com.vishesh.tpc_stud.dashboard.models.NetworkProfile;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vishesh on 25/2/17.
 */

public class NetworkProfileAdapter extends AdapterDelegate<List<NetworkProfile>> {

    private final Context context;

    @Inject
    public NetworkProfileAdapter(Context context) {
        this.context = context;
    }

    @Override
    protected boolean isForViewType(@NonNull List<NetworkProfile> items, int position) {
        return true;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_profile_item, parent, false);
        return new NetworkProfileViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull List<NetworkProfile> items, int position, @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads) {

        NetworkProfile networkProfile = items.get(position);
        NetworkProfileViewHolder networkProfileViewHolder = (NetworkProfileViewHolder) holder;

        networkProfileViewHolder
                .textNetworkProfileLabel
                .setText(networkProfile.getNetwork().getNetworkName());

        networkProfileViewHolder
                .textNetworkProfileAction
                .setText(UiUtils
                        .createSpacedString(context.getString(R.string.network_profile_value),
                                networkProfile.getNetwork().getNetworkName()));
    }

    static class NetworkProfileViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_profile_item_label)
        TextView textNetworkProfileLabel;
        @BindView(R.id.text_profile_item_value)
        TextView textNetworkProfileAction;

        public NetworkProfileViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
