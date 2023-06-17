package com.example.gestionemagazzino.models;


import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionManager {
    private final static String[] NEEDED_PERMISSIONS = {Manifest.permission.READ_CALENDAR};
    private final static String TAG = PermissionManager.class.getCanonicalName();

    private final Activity activity;

    public PermissionManager(Activity activity) {
        this.activity = activity;
    }

    public boolean askNeededPermissions(int requestCode, boolean performRequest) {
        // creo lista nuova dove andrò a mettere i permessi che l'utente non mi ha dato
        List<String> missingPermissions = new ArrayList<>();

        for (String permission : NEEDED_PERMISSIONS) {
            // se utente accetta entro in primo if
            if (ContextCompat.checkSelfPermission(this.activity, permission) ==
                    PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "Permission " + permission + " is granted by the user.");
            } /*
            TODO: Considering showing somehow the permission rationale.
                In this app, we display a very short message in the SplashActivity itself.
            else if (shouldShowRequestPermissionRationale(...)){
                // In an educational UI, explain to the user why your app requires this
                // permission for a specific feature to behave as expected, and what
                // features are disabled if it's declined. In this UI, include a
                // "cancel" or "no thanks" button that lets the user continue
                // using your app without granting the permission.
                showInContextUI(...);
            }*/ else {
                // se l'utente non accetta aggiungo il permesso mancante dentro missin permission che ho creato
                missingPermissions.add(permission);
            }
        }
        // spartiacque tra permessi dati tutti oppure c'è qualcuno che non è garantito
        // se contiene elementi allora nella splash viene restituito...
        if (missingPermissions.isEmpty()) {
            return false;
        }

        /*
        NOTE:
            After some `requestPermissions` calls, Android automatically answer no to the popup.
            To avoid that, the apps usually ask for the permission on one time (first run) and then open the settings activity.
            For instance: https://stackoverflow.com/questions/32822101/how-can-i-programmatically-open-the-permission-screen-for-a-specific-app-on-andr
            ```
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", this.activity.getPackageName(), null);
            intent.setData(uri);
            this.activity.startActivity(intent);
            ```
         */

        if (performRequest) {
            Log.d(TAG, "Request for missing permissions " + missingPermissions);

            // https://developer.android.com/reference/androidx/core/app/ActivityCompat#requestPermissions(android.app.Activity,java.lang.String[],int)
            ActivityCompat.requestPermissions(this.activity, missingPermissions.toArray(new String[missingPermissions.size()]), requestCode);
        }

        return true;
    }

}
