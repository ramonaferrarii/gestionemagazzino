package com.example.gestionemagazzino.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.gestionemagazzino.R;
import com.example.gestionemagazzino.activities.EnterActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MyProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public MyProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AspirationFragment.
     */
    // TODO: Rename and change types and number of parameters
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


    // per mostrare l'account con cui sono loggato
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String userID = user.getEmail();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View externalView = inflater.inflate(R.layout.fragment_myprofile, container, false);
        Button buttonLOGOUT = externalView.findViewById(R.id.B_LOGOUT);
        Button buttonAREARISERVATA = externalView.findViewById(R.id.B_AREA_RISERVATA);

        TextView userIDTextView = (TextView) externalView.findViewById(R.id.userIDTextView);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userID = user.getEmail();
        userIDTextView.setText(userID);

 //per effettuare il LOGOUT
        buttonLOGOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), EnterActivity.class);
                startActivity(intent);
                requireActivity().finish();
            }
        });


        // per accedere all'area riservata
        buttonAREARISERVATA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getChildFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.AccessFragmentContainer);
                if (fragment != null && fragment.isVisible()) {
                    fragmentManager.beginTransaction().hide(fragment).commit();
                    View spaceView = externalView.findViewById(R.id.AccessFragmentContainer);
                    spaceView.setVisibility(View.GONE);
                } else {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    AccessFragment childFragment = new AccessFragment();
                    fragmentTransaction.replace(R.id.AccessFragmentContainer, childFragment)
                            .setReorderingAllowed(true)
                            .addToBackStack("Main Space")
                            .commit();
                    View spaceView = externalView.findViewById(R.id.AccessFragmentContainer);
                    spaceView.setVisibility(View.VISIBLE);
                }
            }
        });

        return externalView;
    }







}