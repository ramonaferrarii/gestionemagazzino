package com.example.gestionemagazzino.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.gestionemagazzino.R;


public class MainSpaceFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public MainSpaceFragment() {
        // Required empty public constructor
    }

    public static MainSpaceFragment newInstance(String param1, String param2) {
        MainSpaceFragment fragment = new MainSpaceFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View externalView = inflater.inflate(R.layout.fragment_main_space, container, false);
        Button buttonUstione = externalView.findViewById(R.id.B_ustione);
        Button buttonParto = externalView.findViewById(R.id.B_parto);

        buttonUstione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.UstioneFragmentContainer);
                if (fragment != null && fragment.isVisible()) {
                    fragmentManager.beginTransaction().hide(fragment).commit();
                    View spaceView = externalView.findViewById(R.id.UstioneFragmentContainer);
                    spaceView.setVisibility(View.GONE);
                } else {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    UstioneFragment childFragment = new UstioneFragment();
                    fragmentTransaction.replace(R.id.UstioneFragmentContainer, childFragment)
                            .setReorderingAllowed(true)
                            .addToBackStack("Ustione")
                            .commit();
                    View spaceView = externalView.findViewById(R.id.UstioneFragmentContainer);
                    spaceView.setVisibility(View.VISIBLE);
                }
            }
        });

        buttonParto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.PartoFragmentContainer);
                if (fragment != null && fragment.isVisible()) {
                    fragmentManager.beginTransaction().hide(fragment).commit();
                    View spaceView = externalView.findViewById(R.id.PartoFragmentContainer);
                    spaceView.setVisibility(View.GONE);
                } else {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    PartoFragment childFragment = new PartoFragment();
                    fragmentTransaction.replace(R.id.PartoFragmentContainer, childFragment)
                            .setReorderingAllowed(true)
                            .addToBackStack("Parto")
                            .commit();
                    View spaceView = externalView.findViewById(R.id.PartoFragmentContainer);
                    spaceView.setVisibility(View.VISIBLE);
                }
            }
        });

        return externalView;
    }
}