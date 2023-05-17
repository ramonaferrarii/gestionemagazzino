package com.example.gestionemagazzino.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gestionemagazzino.R;
import com.example.gestionemagazzino.activities.SplashActivity;
import com.example.gestionemagazzino.models.FirebaseWrapper;

public class LoginFragment {
    public class LoginFragment extends LogFragment {
        @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.initArguments();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            // See: https://developer.android.com/reference/android/view/LayoutInflater#inflate(org.xmlpull.v1.XmlPullParser,%20android.view.ViewGroup,%20boolean)
            View externalView = inflater.inflate(R.layout.fragment_login, container, false);

            TextView link = externalView.findViewById(R.id.switchLoginToRegisterLabel);
            // si riferisce al link di registrazione se non lo si è
            link.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((SplashActivity)LoginFragment.this.requireActivity()).renderFragment(false);
                }
            });

            Button button = externalView.findViewById(R.id.logButton);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText email = externalView.findViewById(R.id.userEmail);
                    EditText password = externalView.findViewById(R.id.userPassword);

                    if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                        // TODO: Better error handling + remove this hardcoded strings
                        email.setError("Email is required");
                        password.setError("Password is required");
                        // set errore è funzione di android e fa apparire nuvola con messaggio di errore
                        return;
                    }

                    // Perform SignIn
                    FirebaseWrapper.Auth auth = new FirebaseWrapper.Auth();
                    auth.signIn(
                            email.getText().toString(),
                            password.getText().toString(),
                            FirebaseWrapper.Callback
                                    .newInstance(LoginFragment.this.requireActivity(),
                                            LoginFragment.this.callbackName,
                                            LoginFragment.this.callbackPrms)
                    );
                }
            });

            return externalView;
        }
    }

}
