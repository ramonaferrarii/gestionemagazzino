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


// This activity contains fragments for login or registration

public class EnterActivity extends AppCompatActivity {
    //Unique tag for the activity
    private static final String TAG = EnterActivity.class.getCanonicalName();
    private FragmentManager fragmentManager = null;
    private Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        // Render fragment is here only called
        renderFragment(true);
       }

       // Render fragment is here definited
    public void renderFragment(boolean isLogin) {
        if(isLogin)
            fragment = LoginFragment.newInstance(LoginFragment.class,"signinCallback", boolean.class);

        else
            fragment = SignupFragment.newInstance(SignupFragment.class,"signinCallback", boolean.class);

        if (this.fragmentManager == null) {
            this.fragmentManager = this.getSupportFragmentManager();
        }

        FragmentTransaction fragmentTransaction = this.fragmentManager.beginTransaction();
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.replace(R.id.loginRegisterFragment, fragment);

        fragmentTransaction.commit();
    }


    // definition of signinCallback
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
    }


}


