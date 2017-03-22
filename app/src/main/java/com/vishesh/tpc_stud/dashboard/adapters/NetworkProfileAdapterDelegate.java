package com.vishesh.tpc_stud.dashboard.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.core.helpers.Bus;
import com.vishesh.tpc_stud.dashboard.busEvents.NetworkProfileTapEvent;
import com.vishesh.tpc_stud.dashboard.models.NetworkProfile;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by vishesh on 25/2/17.
 */
public class NetworkProfileAdapterDelegate extends AdapterDelegate<List<NetworkProfile>> {

    private final Context context;
    private final Bus bus;


    @Inject
    public NetworkProfileAdapterDelegate(Context context,
                                         Bus bus) {
        this.context = context;
        this.bus = bus;
    }

    @Override
    protected boolean isForViewType(@NonNull List<NetworkProfile> items, int position) {
        return true;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_network_profile_item, parent, false);
        return new NetworkProfileViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull List<NetworkProfile> items, int position,
                                    @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads) {

        NetworkProfileViewHolder networkProfileViewHolder = (NetworkProfileViewHolder) holder;

        IconicsDrawable navigateNextIcon = new IconicsDrawable(context)
                .icon(GoogleMaterial.Icon.gmd_navigate_next)
                .sizeDp(16)
                .backgroundColor(ContextCompat.getColor(context, R.color.cardview_dark_background))
                .color(ContextCompat.getColor(context, android.R.color.white));

        networkProfileViewHolder
                .textNetworkProfileItemLabel
                .setText(context.getString(R.string.network_profile_label));

        networkProfileViewHolder
                .imageNetworkProfileNext
                .setImageDrawable(navigateNextIcon);
    }

    class NetworkProfileViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_network_profile_item_label)
        TextView textNetworkProfileItemLabel;
        @BindView(R.id.image_network_profile_item)
        ImageView imageNetworkProfileNext;

        public NetworkProfileViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.layout_network_profile_item)
        void onClick() {
            bus.post(new NetworkProfileTapEvent());
        }

    }
}
