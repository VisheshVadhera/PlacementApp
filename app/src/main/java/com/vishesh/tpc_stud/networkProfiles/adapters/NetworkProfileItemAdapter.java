package com.vishesh.tpc_stud.networkProfiles.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.core.helpers.Bus;
import com.vishesh.tpc_stud.dashboard.models.NetworkProfile;
import com.vishesh.tpc_stud.networkProfiles.busEvents.NetworkProfileItemClickedEvent;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NetworkProfileItemAdapter extends RecyclerView.Adapter<NetworkProfileItemAdapter.ViewHolder> {

    private final Context context;
    private final Bus bus;

    private List<NetworkProfile> networkProfiles;

    @Inject
    public NetworkProfileItemAdapter(Context context, Bus bus) {
        this.context = context;
        this.bus = bus;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_profile_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final NetworkProfile networkProfile = networkProfiles.get(position);

        holder.textProfileItemLabel
                .setText(networkProfile.getNetwork().getNetworkName());

        holder.textProfileItemValue
                .setClickable(true);

        holder.textProfileItemValue
                .setText(context.getString(R.string.network_profile_item_value));

        holder.textProfileItemValue
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bus.post(new NetworkProfileItemClickedEvent(networkProfile));
                    }
                });

    }

    public void setData(List<NetworkProfile> networkProfiles) {
        this.networkProfiles = networkProfiles;
    }

    @Override
    public int getItemCount() {
        return networkProfiles.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_profile_item_label)
        TextView textProfileItemLabel;
        @BindView(R.id.text_profile_item_value)
        TextView textProfileItemValue;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
