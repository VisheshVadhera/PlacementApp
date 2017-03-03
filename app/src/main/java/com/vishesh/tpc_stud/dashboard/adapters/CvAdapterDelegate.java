package com.vishesh.tpc_stud.dashboard.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.core.helpers.Bus;
import com.vishesh.tpc_stud.dashboard.busEvents.CvTapEvent;
import com.vishesh.tpc_stud.dashboard.models.UserProfile;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by vishesh on 25/2/17.
 */

public class CvAdapterDelegate extends AdapterDelegate<UserProfile> {

    private final Context context;
    private final Bus bus;

    @Inject
    public CvAdapterDelegate(Context context,
                             Bus bus) {
        this.context = context;
        this.bus = bus;
    }

    @Override
    protected boolean isForViewType(@NonNull UserProfile userProfile, int position) {
        return true;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_profile_item, parent, false);
        return new CvViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull UserProfile userProfile,
                                    int position,
                                    @NonNull RecyclerView.ViewHolder holder,
                                    @NonNull List<Object> payloads) {

        CvViewHolder cvViewHolder = (CvViewHolder) holder;

        cvViewHolder.textCvLabel.setText(context.getString(R.string.cv_label));

        if (TextUtils.isEmpty(userProfile.getCvUrl())) {
            cvViewHolder.textCvAction.setText(context.getString(R.string.cv_upload_action));
        } else {
            cvViewHolder.textCvAction.setText(context.getString(R.string.cv_view_action));
        }
    }

    private class CvViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_profile_item_value)
        TextView textCvAction;
        @BindView(R.id.text_profile_item_label)
        TextView textCvLabel;

        public CvViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.text_profile_item_value)
        void onClick() {
            bus.post(new CvTapEvent());
        }
    }
}
