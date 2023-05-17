package com.example.gestionemagazzino.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Toast;

import com.example.gestionemagazzino.R;
import com.example.gestionemagazzino.fragments.LoginFragment;
import com.example.gestionemagazzino.fragments.SignupFragment;


// contiene i framment per login o per registrazione


public class EnterActivity extends AppCompatActivity {
    //Tag univoco per l'activity
    private static final String TAG = EnterActivity.class.getCanonicalName();ù
    private FragmentManager fragmentmanager = null;

    // prende layout di activity e la costruisce e va a mettere fragment che va definito come funziona
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        // TODO: Render fragment
        // Render fragment è chiamata solamente
        renderFragment(true);
       }

       // definisco la funzione renderFragment
    public void renderFragment(boolean isLogin) {
        if(isLogin)
            Fragment fragment = LoginFragment.newInstance("signinCallback", boolean.class);
            // TODO: Update to switch between Login and Signup fragments
        else
            Fragment fragment = SignupFragment.newInstance("signinCallback", boolean.class);
        //QUESTION:Non so cosa faccia
        if (this.fragmentManager == null) {
            this.fragmentManager = this.getSupportFragmentManager();
        }

        FragmentTransaction fragmentTransaction = this.fragmentManager.beginTransaction();

        // For optimizations -- See: https://developer.android.com/reference/androidx/fragment/app/FragmentTransaction#setReorderingAllowed(boolean)
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.replace(R.id.loginRegisterFragment, fragment);

        fragmentTransaction.commit();
    }


    // definisco funzione di callback
    public void signinCallback(boolean result) {
        if (!result) {
            // TODO: Better handling of the error message --> e.g., put in a textview of the activity/fragment
            Toast
                    .makeText(this, "Username or password are not valid", Toast.LENGTH_LONG)
                    .show();
        } else {
            // Go To Splash to check the permissions
            Intent intent = new Intent(this, SplashActivity.class);
            this.startActivity(intent);
            this.finish();
        }
    } public void signinCallback(boolean result) {
        if (!result) {
            // TODO: Better handling of the error message --> e.g., put in a textview of the activity/fragment
            Toast
                    .makeText(this, "Username or password are not valid", Toast.LENGTH_LONG)
                    .show();
        } else {
            // Go To Splash to check the permissions
            Intent intent = new Intent(this, SplashActivity.class);
            this.startActivity(intent);
            this.finish();
        }
    }


}


