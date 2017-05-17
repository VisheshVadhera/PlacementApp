package com.vishesh.placement.core.utils;

import android.content.Context;
import android.widget.Toast;

class UiUtils {

    private UiUtils() {
        throw new AssertionError("Can't instantiate UiUtils");
    }

    public static void showToast(Context context, String toastMessage){
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT)
                .show();
    }
}
