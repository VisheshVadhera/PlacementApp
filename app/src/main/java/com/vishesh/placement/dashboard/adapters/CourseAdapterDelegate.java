package com.vishesh.placement.dashboard.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import com.vishesh.placement.R;
import com.vishesh.placement.core.utils.StringFormatUtils;
import com.vishesh.placement.dashboard.models.UserProfile;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CourseAdapterDelegate extends AdapterDelegate<UserProfile> {

    private final Context context;

    @Inject
    public CourseAdapterDelegate(Context context) {
        this.context = context;
    }

    @Override
    protected boolean isForViewType(@NonNull UserProfile items, int position) {
        return true;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_profile_item, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull UserProfile userProfile,
                                    int position,
                                    @NonNull RecyclerView.ViewHolder holder,
                                    @NonNull List<Object> payloads) {

        CourseViewHolder courseViewHolder = (CourseViewHolder) holder;

        courseViewHolder.textCourseLabel.setText(context.getString(R.string.course_label));
        courseViewHolder.textCourseValue.setText(StringFormatUtils
                .createSpacedString(userProfile.getCourse().getBranch(),
                        userProfile.getCourse().getDegree().getDegreeName()));
    }

    static class CourseViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_profile_item_label)
        TextView textCourseLabel;
        @BindView(R.id.text_profile_item_value)
        TextView textCourseValue;

        public CourseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
