package com.example.gestionemagazzino.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gestionemagazzino.R;
import com.example.gestionemagazzino.activities.EnterActivity;
import com.example.gestionemagazzino.models.FirebaseWrapper;

public class SignupFragment extends LogFragment {

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View externalView = inflater.inflate(R.layout.fragment_signup, container, false);

        TextView link = externalView.findViewById(R.id.switchRegisterToLoginLabel);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((EnterActivity) SignupFragment.this.requireActivity()).renderFragment(true);
            }
        });

        Button button = externalView.findViewById(R.id.logButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText email = externalView.findViewById(R.id.userEmail);
                EditText password = externalView.findViewById(R.id.userPassword);
                EditText password2 = externalView.findViewById(R.id.userPasswordAgain);

                if (email.getText().toString().isEmpty() ||
                        password.getText().toString().isEmpty() ||
                        password2.getText().toString().isEmpty()) {
                    email.setError("Email is required");
                    password.setError("Password is required");
                    password2.setError("Password is required");
                    return;
                }

                if (!password.getText().toString().equals(password2.getText().toString())) {
                    Toast
                            .makeText(SignupFragment.this.requireActivity(), "Passwords are different", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                // Perform SignIn
                FirebaseWrapper.Auth auth = new FirebaseWrapper.Auth();
                auth.signUp(
                        email.getText().toString(),
                        password.getText().toString(),
                        FirebaseWrapper.Callback
                                .newInstance(SignupFragment.this.requireActivity(),
                                        SignupFragment.this.callbackName,
                                        SignupFragment.this.callbackPrms)
                );
            }
        });

        return externalView;
    }
}
