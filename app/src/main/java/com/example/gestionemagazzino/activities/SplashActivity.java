package com.example.gestionemagazzino.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.gestionemagazzino.R;

import java.util.Random;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    private static final String TAG = SplashActivity.class.getCanonicalName();

    // Only positive int
    private static final int PERMISSION_REQUEST_CODE = (new Random()).nextInt() & Integer.MAX_VALUE;

    private void goToActivity(Class<?> activity) {
        Intent intent = new Intent(this, activity);
        this.startActivity(intent);
        this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

       /* // TODO: check user
        // If not --> log
        // Firebase auth: https://firebase.google.com/docs/auth/android/start?hl=en#java
        FirebaseWrapper.Auth auth = new FirebaseWrapper.Auth();
         utente è loggato o no? se non lo è allora chiamo l'enter activity
        if (!auth.isAuthenticated()) {
            // Go to Activity for LogIn or SignUp
            this.goToActivity(EnterActivity.class);
        } */

        // Check permissions -- Do not request at the login!
        PermissionManager pm = new PermissionManager(this);
        if (!pm.askNeededPermissions(PERMISSION_REQUEST_CODE, false)) {
            // se i permessi sono garantiti vado alla main activity
            // Go to MainActivity
            this.goToActivity(MainActivity.class);
        }

        // Add the listener to the 'Grant Now' permission
        this.findViewById(R.id.grantPermission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!pm.askNeededPermissions(PERMISSION_REQUEST_CODE, true)) {
                    // Go to MainActivity
                    SplashActivity.this.goToActivity(EnterActivity.class);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode != PERMISSION_REQUEST_CODE) {
            return;
        }

        for (int res : grantResults) {
            if (res == PackageManager.PERMISSION_DENIED) {
                Log.w(TAG, "A needed permission is not granted!");
                return;
            }
        }

        // All permissions are granted
        Log.d(TAG, "All the needed permissions are granted!");
        this.goToActivity(MainActivity.class);
    }}
