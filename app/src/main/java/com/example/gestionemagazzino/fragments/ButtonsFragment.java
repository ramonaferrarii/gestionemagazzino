package com.example.gestionemagazzino.fragments;

// è il primo fragment che andrà dentro il container e in cui definisco i bottoni
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
 * Use the {@link ButtonsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ButtonsFragment extends Fragment  {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ButtonsFragment() {
        // Required empty public constructor


    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ButtonsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ButtonsFragment newInstance(String param1, String param2) {
        ButtonsFragment fragment = new ButtonsFragment();
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
        View externalView = inflater.inflate(R.layout.fragment_buttons, container, false);

        // creazione dei listener per ogni bottone
        Button buttonMainSpace = externalView.findViewById(R.id.B_mainspace);
        Button buttonAspiration = externalView.findViewById(R.id.B_aspiraz);
        Button buttonDae = externalView.findViewById(R.id.B_dae);
        Button buttonBackpack = externalView.findViewById(R.id.B_backpack);
        Button buttonChildpack = externalView.findViewById(R.id.B_childpack);
        Button buttonMisc = externalView.findViewById(R.id.B_misc);
        Button buttonDpi = externalView.findViewById(R.id.B_dpi);
        Button buttonTrauma = externalView.findViewById(R.id.B_trauma);
        Button buttonFrontspace = externalView.findViewById(R.id.B_frontspace);

        // divisione di cosa succede nelle varie casistiche se schiaccio un bottone piuttosto che un altro
        buttonMainSpace.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();

                MainSpaceFragment childFragment = new MainSpaceFragment();
                fragmentTransaction.add(R.id.mainSpaceFragmentContainer, childFragment)
                        .setReorderingAllowed(true)
                        .addToBackStack("mainSpace") // Name can be null
                        .commit();
                View spaceView=externalView.findViewById(R.id.mainSpaceFragmentContainer);
                spaceView.setVisibility(View.VISIBLE);

            }
        });
        buttonAspiration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();

                AspirationFragment childFragment = new AspirationFragment();
                fragmentTransaction.add(R.id.AspirationFragmentContainer, childFragment)
                        .setReorderingAllowed(true)
                        .addToBackStack("Aspiration") // Name can be null
                        .commit();
                View spaceView=externalView.findViewById(R.id.AspirationFragmentContainer);
                spaceView.setVisibility(View.VISIBLE);
            }
        });
        buttonDae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                DaeFragment childFragment = new DaeFragment();
                fragmentTransaction.add(R.id.DaeFragmentContainer, childFragment)
                        .setReorderingAllowed(true)
                        .addToBackStack("Dae") // Name can be null
                        .commit();
                View spaceView=externalView.findViewById(R.id.DaeFragmentContainer);
                spaceView.setVisibility(View.VISIBLE);
            }
        });
        buttonChildpack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                ChildpackFragment childFragment = new ChildpackFragment();
                fragmentTransaction.add(R.id.ChildpackFragmentContainer, childFragment)
                        .setReorderingAllowed(true)
                        .addToBackStack("Childpack") // Name can be null
                        .commit();
                View spaceView=externalView.findViewById(R.id.ChildpackFragmentContainer);
                spaceView.setVisibility(View.VISIBLE);
            }
        });
        buttonMisc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                MiscFragment childFragment = new MiscFragment();
                fragmentTransaction.add(R.id.MiscFragmentContainer, childFragment)
                        .setReorderingAllowed(true)
                        .addToBackStack("Misc") // Name can be null
                        .commit();
                View spaceView=externalView.findViewById(R.id.MiscFragmentContainer);
                spaceView.setVisibility(View.VISIBLE);
            }
        });
        buttonDpi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                DpiFragment childFragment = new DpiFragment();
                fragmentTransaction.add(R.id.DpiFragmentContainer, childFragment)
                        .setReorderingAllowed(true)
                        .addToBackStack("Dpi") // Name can be null
                        .commit();
                View spaceView=externalView.findViewById(R.id.DpiFragmentContainer);
                spaceView.setVisibility(View.VISIBLE);
            }
        });
        buttonFrontspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                FrontspaceFragment childFragment = new FrontspaceFragment();
                fragmentTransaction.add(R.id.FrontspaceFragmentContainer, childFragment)
                        .setReorderingAllowed(true)
                        .addToBackStack("Frontspace") // Name can be null
                        .commit();
                View spaceView=externalView.findViewById(R.id.FrontspaceFragmentContainer);
                spaceView.setVisibility(View.VISIBLE);
            }
        });
        buttonTrauma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                TraumaFragment childFragment = new TraumaFragment();
                fragmentTransaction.add(R.id.TraumaFragmentContainer, childFragment)
                        .setReorderingAllowed(true)
                        .addToBackStack("Trauma") // Name can be null
                        .commit();
                View spaceView=externalView.findViewById(R.id.TraumaFragmentContainer);
                spaceView.setVisibility(View.VISIBLE);
            }
        });
        buttonBackpack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();

                BackpackFragment childFragment = new BackpackFragment();
                fragmentTransaction.add(R.id.BackpackFragmentContainer, childFragment)
                        .setReorderingAllowed(true)
                        .addToBackStack("mainSpace") // Name can be null
                        .commit();
                View spaceView=externalView.findViewById(R.id.BackpackFragmentContainer);
                spaceView.setVisibility(View.VISIBLE);

            }
        });

        return externalView;
    }
}


