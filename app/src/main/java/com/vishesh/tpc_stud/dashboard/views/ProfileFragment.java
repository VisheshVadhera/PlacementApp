package com.vishesh.tpc_stud.dashboard.views;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.core.helpers.Bus;
import com.vishesh.tpc_stud.common.models.User;
import com.vishesh.tpc_stud.core.utils.FileUtils;
import com.vishesh.tpc_stud.core.views.BaseFragment;
import com.vishesh.tpc_stud.dashboard.adapters.GpaAdapterDelegate;
import com.vishesh.tpc_stud.dashboard.adapters.NetworkProfileAdapterDelegate;
import com.vishesh.tpc_stud.dashboard.adapters.ProfileItemAdapter;
import com.vishesh.tpc_stud.dashboard.busEvents.CvTapEvent;
import com.vishesh.tpc_stud.dashboard.models.UserProfile;
import com.vishesh.tpc_stud.dashboard.presenters.ProfilePresenter;
import com.vishesh.tpc_stud.networkProfiles.views.NetworkProfilesFragment;
import com.vishesh.tpc_stud.semesterGrades.views.SemesterGradesFragment;

import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.functions.Consumer;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class ProfileFragment
        extends BaseFragment
        implements ProfilePresenter.ProfileView,
        GpaAdapterDelegate.GpaClickListener,
        NetworkProfileAdapterDelegate.NetworkProfileClickListener{

    private static final int FILE_SELECT_REQUEST_CODE = 1001;

    @BindView(R.id.recycler_view_profile)
    RecyclerView recyclerViewProfile;

    @Inject
    ProfileItemAdapter profileItemAdapter;
    @Inject
    ProfilePresenter profilePresenter;
    @Inject
    Bus bus;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    protected void injectDependencies() {
        getDependencyInjector()
                .inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profilePresenter.setProfileView(this);
        if (savedInstanceState == null) {
            loadProfile();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        profilePresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        profilePresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recyclerViewProfile.setAdapter(null);
        unbinder.unbind();
    }

    @Override
    public void onStart() {
        super.onStart();
        bus.asFlowable()
                .subscribe(new BusEventConsumer());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        profilePresenter.destroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    public void showProfile(User user, UserProfile userProfile) {
        profileItemAdapter.setData(user, userProfile);
        recyclerViewProfile.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewProfile.setAdapter(profileItemAdapter);

        profileItemAdapter.setGpaClickListener(this);
        profileItemAdapter.setNetworkProfileClickListener(this);
    }

    @Override
    public void openFileExplorer(String fileType) {
        ProfileFragmentPermissionsDispatcher.showFileChooserWithCheck(this, fileType);
    }

    @Override
    public void openPdfViewer(String pdfUrl) {
        Intent pdfIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pdfUrl));
        pdfIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(pdfIntent);
    }

    @Override
    public void openNetworkProfilesScreen() {
        Intent networkProfilesIntent = NetworkProfilesFragment.createIntent(getContext());
        startActivity(networkProfilesIntent);
    }

    @Override
    public void openSemesterGradesScreen() {
        Intent semesterGradesIntent = SemesterGradesFragment.createIntent(getActivity());
        startActivity(semesterGradesIntent);
    }

    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    void showFileChooser(String fileType) {
        Intent fileChooserIntent = new Intent(Intent.ACTION_GET_CONTENT);
        fileChooserIntent.setType(fileType);
        fileChooserIntent.addCategory(Intent.CATEGORY_OPENABLE);

        if (fileChooserIntent.resolveActivity(getContext().getPackageManager()) != null) {
            startActivityForResult(Intent.createChooser(fileChooserIntent,
                    getString(R.string.file_chooser_title)), FILE_SELECT_REQUEST_CODE);
        } else {
            showMessage(getString(R.string.file_chooser_err));
        }
    }

    @OnPermissionDenied(Manifest.permission.READ_EXTERNAL_STORAGE)
    void showDeniedReadExternalStorage() {
        showMessage(getString(R.string.file_chooser_permission_denied));
    }

    @OnNeverAskAgain(Manifest.permission.READ_EXTERNAL_STORAGE)
    void showNeverAskForExternalStorage() {
        showMessage(getString(R.string.file_chooser_never_ask));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ProfileFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case FILE_SELECT_REQUEST_CODE:
                    File file = FileUtils.getFileForUri(getContext(), data.getData());
                    profilePresenter.onFileReceived(file);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void loadProfile() {
        profilePresenter.initialize();
    }

    @Override
    public void onGpaClicked() {
        profilePresenter.onGpaTapped();
    }

    @Override
    public void onNetworkProfileClicked() {
        profilePresenter.onNetworkProfileTapped();
    }

    private class BusEventConsumer implements Consumer<Object> {

        @Override
        public void accept(Object event) throws Exception {
            if (event instanceof CvTapEvent) {
                profilePresenter.onCvTapped();
            } /*else if (event instanceof NetworkProfileTapEvent) {
                profilePresenter.onNetworkProfileTapped();
            } else if (event instanceof GpaTappedEvent) {
                profilePresenter.onGpaTapped();
            }*/
        }
    }
}
