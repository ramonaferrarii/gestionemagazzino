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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainSpaceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainSpaceFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainSpaceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainSpaceFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_main_space, container, false);

        // creazione dei listener per i bottoni
        View externalView = inflater.inflate(R.layout.fragment_main_space, container, false);
        Button buttonUstione = externalView.findViewById(R.id.B_ustione);
        Button buttonParto = externalView.findViewById(R.id.B_parto);

        // se schiaccio un bottone piuttosto che un altro vediamo cosa succede:
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