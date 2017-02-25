package com.vishesh.tpc_stud.dashboard.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.vishesh.tpc_stud.core.models.User;
import com.vishesh.tpc_stud.dashboard.models.UserProfile;

import javax.inject.Inject;

/**
 * Created by vishesh on 24/2/17.
 */

public class ProfileItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_GPA = 1;
    private static final int VIEW_TYPE_CV = 2;
    private static final int VIEW_TYPE_COURSE = 3;
    private static final int VIEW_TYPE_NETWORK_PROFILE = 4;

    private final HeaderAdapterDelegate headerAdapterDelegate;
    private final GpaAdapterDelegate gpaAdapterDelegate;
    private final CvAdapterDelegate cvAdapterDelegate;
    private final CourseAdapterDelegate courseAdapterDelegate;
    private final NetworkProfileAdapter networkProfileAdapter;

    private User user;
    private UserProfile userProfile;

    @Inject
    public ProfileItemAdapter(HeaderAdapterDelegate headerAdapterDelegate,
                              GpaAdapterDelegate gpaAdapterDelegate,
                              CvAdapterDelegate cvAdapterDelegate,
                              CourseAdapterDelegate courseAdapterDelegate,
                              NetworkProfileAdapter networkProfileAdapter) {
        this.headerAdapterDelegate = headerAdapterDelegate;
        this.gpaAdapterDelegate = gpaAdapterDelegate;
        this.cvAdapterDelegate = cvAdapterDelegate;
        this.courseAdapterDelegate = courseAdapterDelegate;
        this.networkProfileAdapter = networkProfileAdapter;
    }

    public void setData(User user, UserProfile userProfile){
        this.user = user;
        this.userProfile = userProfile;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_HEADER:
                return headerAdapterDelegate.onCreateViewHolder(parent);
            case VIEW_TYPE_GPA:
                return gpaAdapterDelegate.onCreateViewHolder(parent);
            case VIEW_TYPE_CV:
                return cvAdapterDelegate.onCreateViewHolder(parent);
            case VIEW_TYPE_COURSE:
                return courseAdapterDelegate.onCreateViewHolder(parent);
            default:
                return networkProfileAdapter.onCreateViewHolder(parent);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_HEADER:
                headerAdapterDelegate.onBindViewHolder(user, position, holder, null);
                break;
            case VIEW_TYPE_GPA:
                gpaAdapterDelegate.onBindViewHolder(userProfile, position, holder, null);
                break;
            case VIEW_TYPE_CV:
                cvAdapterDelegate.onBindViewHolder(userProfile, position, holder, null);
                break;
            case VIEW_TYPE_COURSE:
                courseAdapterDelegate.onBindViewHolder(userProfile, position, holder, null);
                break;
            default:
                networkProfileAdapter.onBindViewHolder(userProfile.getNetworkProfiles(),
                        position, holder, null);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_HEADER;
        } else if (position == 1) {
            return VIEW_TYPE_GPA;
        } else if (position == 2) {
            return VIEW_TYPE_CV;
        } else if (position == 3) {
            return VIEW_TYPE_COURSE;
        } else {
            return VIEW_TYPE_NETWORK_PROFILE;
        }
    }

    @Override
    public int getItemCount() {
        return userProfile.getNetworkProfiles().size() + 4;
    }
}
