package com.vishesh.tpc_stud.dashboard.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.vishesh.tpc_stud.common.models.User;
import com.vishesh.tpc_stud.dashboard.models.UserProfile;

import javax.inject.Inject;

public class ProfileItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_GPA = 1;
    private static final int VIEW_TYPE_CV = 2;
    private static final int VIEW_TYPE_COURSE = 3;
    private static final int VIEW_TYPE_NETWORK_PROFILE = 4;

    private final ProfileHeaderAdapterDelegate profileHeaderAdapterDelegate;
    private final GpaAdapterDelegate gpaAdapterDelegate;
    private final CvAdapterDelegate cvAdapterDelegate;
    private final CourseAdapterDelegate courseAdapterDelegate;
    private final NetworkProfileAdapterDelegate networkProfileAdapterDelegate;

    private User user;
    private UserProfile userProfile;

    @Inject
    public ProfileItemAdapter(ProfileHeaderAdapterDelegate profileHeaderAdapterDelegate,
                              GpaAdapterDelegate gpaAdapterDelegate,
                              CvAdapterDelegate cvAdapterDelegate,
                              CourseAdapterDelegate courseAdapterDelegate,
                              NetworkProfileAdapterDelegate networkProfileAdapterDelegate) {
        this.profileHeaderAdapterDelegate = profileHeaderAdapterDelegate;
        this.gpaAdapterDelegate = gpaAdapterDelegate;
        this.cvAdapterDelegate = cvAdapterDelegate;
        this.courseAdapterDelegate = courseAdapterDelegate;
        this.networkProfileAdapterDelegate = networkProfileAdapterDelegate;
    }

    public void setData(User user, UserProfile userProfile) {
        this.user = user;
        this.userProfile = userProfile;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_HEADER:
                return profileHeaderAdapterDelegate.onCreateViewHolder(parent);
            case VIEW_TYPE_GPA:
                return gpaAdapterDelegate.onCreateViewHolder(parent);
            case VIEW_TYPE_CV:
                return cvAdapterDelegate.onCreateViewHolder(parent);
            case VIEW_TYPE_COURSE:
                return courseAdapterDelegate.onCreateViewHolder(parent);
            default:
                return networkProfileAdapterDelegate.onCreateViewHolder(parent);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_HEADER:
                profileHeaderAdapterDelegate.onBindViewHolder(user, position, holder, null);
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
                networkProfileAdapterDelegate.onBindViewHolder(userProfile.getNetworkProfiles(),
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
        return 5;
    }

    public void setGpaClickListener(GpaAdapterDelegate.GpaClickListener gpaClickListener) {
        gpaAdapterDelegate.setGpaClickListener(gpaClickListener);
    }


    public void setNetworkProfileClickListener(NetworkProfileAdapterDelegate.NetworkProfileClickListener listener) {
        networkProfileAdapterDelegate.setNetworkProfileClickListener(listener);
    }
}
