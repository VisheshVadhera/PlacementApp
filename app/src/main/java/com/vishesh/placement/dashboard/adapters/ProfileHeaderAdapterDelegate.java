package com.vishesh.placement.dashboard.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import com.squareup.picasso.Picasso;
import com.vishesh.placement.R;
import com.vishesh.placement.common.models.User;
import com.vishesh.placement.core.utils.StringFormatUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileHeaderAdapterDelegate extends AdapterDelegate<User> {

    private final Context context;

    @Inject
    public ProfileHeaderAdapterDelegate(Context context) {
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

        Picasso.with(context)
                .load(user.getPicUrl())
                .into(headerViewHolder.imageProfilePicture);

        headerViewHolder.textFullName.setText(StringFormatUtils.createSpacedString(user.getFirstName(), user.getLastName()));
        headerViewHolder.textEmail.setText(user.getEmail());
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {

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
