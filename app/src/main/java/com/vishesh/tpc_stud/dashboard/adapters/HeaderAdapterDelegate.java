package com.vishesh.tpc_stud.dashboard.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.core.models.User;
import com.vishesh.tpc_stud.core.utils.UiUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by vishesh on 25/2/17.
 */
public class HeaderAdapterDelegate extends AdapterDelegate<User> {

    private final Context context;

    @Inject
    public HeaderAdapterDelegate(Context context) {
        this.context = context;
    }

    @Override
    protected boolean isForViewType(@NonNull User items, int position) {
        return true;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_profile_header, parent, false);
        return new HeaderViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull User user, int position,
                                    @NonNull RecyclerView.ViewHolder holder,
                                    @NonNull List<Object> payloads) {
        HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;

        Glide.with(context)
                .load(user.getPicUrl())
                .into(headerViewHolder.imageProfilePicture);

        headerViewHolder.textFullName.setText(UiUtils.createSpacedString(user.getFirstName(), user.getLastName()));
        headerViewHolder.textEmail.setText(user.getEmail());
    }

    private static class HeaderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_profile_picture)
        CircleImageView imageProfilePicture;
        @BindView(R.id.text_full_name)
        TextView textFullName;
        @BindView(R.id.text_email)
        TextView textEmail;

        HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
