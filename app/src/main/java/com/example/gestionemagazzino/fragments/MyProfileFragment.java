package com.example.gestionemagazzino.fragments;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.gestionemagazzino.R;
import com.example.gestionemagazzino.activities.EnterActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MyProfileFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public MyProfileFragment() {
        // Required empty public constructor
    }

    public static MyProfileFragment newInstance(String param1, String param2) {
        MyProfileFragment fragment = new MyProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    // To show the logged EMAIL
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String userID = user.getEmail();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View externalView = inflater.inflate(R.layout.fragment_myprofile, container, false);
        Button buttonLOGOUT = externalView.findViewById(R.id.B_LOGOUT);
        EditText editText = externalView.findViewById(R.id.ET_Pass);
        Button buttonAREARISERVATA = externalView.findViewById(R.id.B_ENTER_AR);
        Button buttonCONTATTI = externalView.findViewById(R.id.B_CONTATTI);


        TextView userIDTextView = (TextView) externalView.findViewById(R.id.userIDTextView);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userID = user.getEmail();
        userIDTextView.setText(userID);

 // To do the LOGOUT
        buttonLOGOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), EnterActivity.class);
                startActivity(intent);
                requireActivity().finish();
            }
        });


        // To enter in the AREA RISERVATA
        buttonAREARISERVATA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // password is HERE hardcoded for educational purposes only, the final version should keep the password remotely
                String pass="admin";
                if(editText.getText().toString().equals(pass)){
                    AreaRiservataFragment fragment = new AreaRiservataFragment();
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction
                            .replace(R.id.fragment_container_view, fragment)
                            .addToBackStack("AreaRiservata")
                            .commit();

                }
                else
                    Toast.makeText(externalView.getContext(), "password incorretta", Toast.LENGTH_SHORT).show();

            }
        });

        buttonCONTATTI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crea un content resolver
                ContentResolver resolver = getContentResolver();

                // it means a projection:we consider only the name
                String[] projection = new String[]{
                        ContactsContract.Contacts.DISPLAY_NAME
                };

                // Selection: only contacts with at least one phone number
                String selection =
                        ContactsContract.Contacts.HAS_PHONE_NUMBER + " = ?";

                String[] selectionArgs =
                        new String[]{"1"};

                //Sort ascendingly by name
                String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " ASC";

                 // Doing the query
                Cursor cursor = resolver.query(ContactProvider.CONTENT_URI,
                        projection, selection, selectionArgs, sortOrder);

// Scorri il cursor per recuperare i dati
                while (cursor.moveToNext()) {
                    @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.Contacts.DISPLAY_NAME));

                    Log.d(TAG, "Name: " + name);
                }

                cursor.close();

            }

        });

        return externalView;
    }


    public ContentResolver getContentResolver() {
        return getContext().getContentResolver();
    }

}