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
import com.example.gestionemagazzino.models.FirebaseWrapper;
import com.example.gestionemagazzino.models.PermissionManager;

import java.util.Random;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = SplashActivity.class.getCanonicalName();

    private static final int PERMISSION_REQUESTE_CODE = (new Random()).nextInt() & Integer.MAX_VALUE;

    // COSA SERVE?
    private void goToActivity(Class<?> activity) {
        Intent intent = new Intent(this, activity);
        this.startActivity(intent);
        this.finish();
    }
    @Override
    protected void OnCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //prima di controllare i permessi, verifico che l'utente sia loggato, in caso negativo
        //avvio l'autenticazione
        // TODO: check user
        // If not --> log
        // Firebase auth: https://firebase.google.com/docs/auth/android/start?hl=en#java
        FirebaseWrapper.Auth auth = new FirebaseWrapper.Auth();
        if (!auth.isAuthenticated()) {
            // Go to Activity for LogIn or SignUp
            this.goToActivity(EnterActivity.class);


        }


}
