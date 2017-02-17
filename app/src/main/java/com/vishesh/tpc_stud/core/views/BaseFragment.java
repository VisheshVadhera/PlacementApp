package com.vishesh.tpc_stud.core.views;

import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * Created by vishesh on 17/2/17.
 */
public abstract class BaseFragment extends Fragment {

    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT)
                .show();
    }
}
