package com.vishesh.tpc_stud.core.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by vishesh on 24/2/17.
 */

public class UiUtils {

    private UiUtils() {
        throw new AssertionError("Can't instantiate UiUtils");
    }

    public static void showToast(Context context, String toastMessage){
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT)
                .show();
    }
}
