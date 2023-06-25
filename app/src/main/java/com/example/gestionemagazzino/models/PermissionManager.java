package com.example.gestionemagazzino.models;


import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;


public class PermissionManager {
    private final static String[] NEEDED_PERMISSIONS = {Manifest.permission.ACCESS_COARSE_LOCATION,"."};
    private final static String TAG = PermissionManager.class.getCanonicalName();

    private final Activity activity;

    public PermissionManager(Activity activity) {
        this.activity = activity;
    }

    public boolean askNeededPermissions(int requestCode, boolean performRequest) {
        // New list where there are granted permissions
        List<String> missingPermissions = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            NEEDED_PERMISSIONS[1]=Manifest.permission.POST_NOTIFICATIONS;

        for (String permission : NEEDED_PERMISSIONS) {
            // If the user accepts --> first if
            if (ContextCompat.checkSelfPermission(this.activity, permission) ==
                    PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "Permission " + permission + " is granted by the user.");
            } else {
                // if the user does not accept --> adding the missing permission in missingPermissions
                missingPermissions.add(permission);
            }
        }

        if (missingPermissions.isEmpty()) {
            return false;
        }


        if (performRequest) {
            Log.d(TAG, "Request for missing permissions " + missingPermissions);
            ActivityCompat.requestPermissions(this.activity, missingPermissions.toArray(new String[missingPermissions.size()]), requestCode);
        }

        return true;
    }

}
