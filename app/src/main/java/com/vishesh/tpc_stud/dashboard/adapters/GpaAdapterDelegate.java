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
import com.vishesh.tpc_stud.core.helpers.Bus;
import com.vishesh.tpc_stud.dashboard.busEvents.GpaTappedEvent;
import com.vishesh.tpc_stud.dashboard.models.UserProfile;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GpaAdapterDelegate extends AdapterDelegate<UserProfile> {

    private final Context context;
    private final Bus bus;

    @Inject
    public GpaAdapterDelegate(Context context,
                              Bus bus) {
        this.context = context;
        this.bus = bus;
    }

    @Override
    protected boolean isForViewType(@NonNull UserProfile items, int position) {
        return true;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_profile_item, parent, false);
        return new GpaViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull UserProfile userProfile,
                                    int position,
                                    @NonNull RecyclerView.ViewHolder holder,
                                    @NonNull List<Object> payloads) {

        GpaViewHolder gpaViewHolder = (GpaViewHolder) holder;

        gpaViewHolder.textGpaLabel.setText(context.getString(R.string.gpa_label));
        gpaViewHolder.textGpaValue.setText(userProfile.getGpa().toString());
    }

    class GpaViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_profile_item_label)
        TextView textGpaLabel;
        @BindView(R.id.text_profile_item_value)
        TextView textGpaValue;

        public GpaViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.layout_profile_item)
        public void onClick(){
            bus.post(new GpaTappedEvent());
        }

    }
}
