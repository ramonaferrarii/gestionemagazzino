package com.example.gestionemagazzino.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gestionemagazzino.R;

public class BackpackFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public BackpackFragment() {
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
        // Creation of Listeners
        View externalView = inflater.inflate(R.layout.fragment_backpack, container, false);
        Button buttonkitambu = externalView.findViewById(R.id.B_kitambu);
        Button buttonmedicazione = externalView.findViewById(R.id.B_medicazione);

        // Cases of bottons
        buttonkitambu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.kitambuFragmentContainer);
                if (fragment != null && fragment.isVisible()) {
                    fragmentManager.beginTransaction().hide(fragment).commit();
                    View spaceView = externalView.findViewById(R.id.kitambuFragmentContainer);
                    spaceView.setVisibility(View.GONE);
                } else {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    KitAmbuFragment childFragment = new KitAmbuFragment();
                    fragmentTransaction.replace(R.id.kitambuFragmentContainer, childFragment)
                            .setReorderingAllowed(true)
                            .addToBackStack("Kit Ambu")
                            .commit();
                    View spaceView = externalView.findViewById(R.id.kitambuFragmentContainer);
                    spaceView.setVisibility(View.VISIBLE);
                }
            }
        });

        buttonmedicazione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.MedicazioneFragmentContainer);
                if (fragment != null && fragment.isVisible()) {
                    fragmentManager.beginTransaction().hide(fragment).commit();
                    View spaceView = externalView.findViewById(R.id.MedicazioneFragmentContainer);
                    spaceView.setVisibility(View.GONE);
                } else {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    MedicazioneFragment childFragment = new MedicazioneFragment();
                    fragmentTransaction.replace(R.id.MedicazioneFragmentContainer, childFragment)
                            .setReorderingAllowed(true)
                            .addToBackStack("Parto")
                            .commit();
                    View spaceView = externalView.findViewById(R.id.MedicazioneFragmentContainer);
                    spaceView.setVisibility(View.VISIBLE);
                }
            }
        });

        return externalView;
    }
}
