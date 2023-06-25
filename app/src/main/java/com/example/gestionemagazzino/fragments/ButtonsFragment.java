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

public class ButtonsFragment extends Fragment  {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public ButtonsFragment() {
        // Required empty public constructor
    }

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

        View externalView = inflater.inflate(R.layout.fragment_buttons, container, false);

        // Listeners
        Button buttonMainSpace = externalView.findViewById(R.id.B_mainspace);
        Button buttonAspiration = externalView.findViewById(R.id.B_aspiraz);
        Button buttonDae = externalView.findViewById(R.id.B_dae);
        Button buttonBackpack = externalView.findViewById(R.id.B_backpack);
        Button buttonChildpack = externalView.findViewById(R.id.B_childpack);
        Button buttonMisc = externalView.findViewById(R.id.B_misc);
        Button buttonDpi = externalView.findViewById(R.id.B_dpi);
        Button buttonTrauma = externalView.findViewById(R.id.B_trauma);
        Button buttonFrontspace = externalView.findViewById(R.id.B_frontspace);

        // Cases of Buttons
        buttonMainSpace.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.mainSpaceFragmentContainer);
                //questo costrutto condizionale risolve il problema della chiusura delle tendine in caso di tocchi successivi
                if (fragment != null && fragment.isVisible()) {
                    fragmentManager.beginTransaction().hide(fragment).commit();
                    View spaceView = externalView.findViewById(R.id.mainSpaceFragmentContainer);
                    spaceView.setVisibility(View.GONE);
                } else {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    MainSpaceFragment childFragment = new MainSpaceFragment();
                    fragmentTransaction.replace(R.id.mainSpaceFragmentContainer, childFragment)
                            .setReorderingAllowed(true)
                            .addToBackStack("Main Space")
                            .commit();
                    View spaceView = externalView.findViewById(R.id.mainSpaceFragmentContainer);
                    spaceView.setVisibility(View.VISIBLE);
                }
            }
        });
        buttonAspiration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.AspirationFragmentContainer);
                if (fragment != null && fragment.isVisible()) {
                    fragmentManager.beginTransaction().hide(fragment).commit();
                    View spaceView = externalView.findViewById(R.id.AspirationFragmentContainer);
                    spaceView.setVisibility(View.GONE);
                } else {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    AspirationFragment childFragment = new AspirationFragment();
                    fragmentTransaction.replace(R.id.AspirationFragmentContainer, childFragment)
                            .setReorderingAllowed(true)
                            .addToBackStack("Aspiration")
                            .commit();
                    View spaceView = externalView.findViewById(R.id.AspirationFragmentContainer);
                    spaceView.setVisibility(View.VISIBLE);
                }
            }
        });
        buttonDae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.DaeFragmentContainer);
                if (fragment != null && fragment.isVisible()) {
                    fragmentManager.beginTransaction().hide(fragment).commit();
                    View spaceView = externalView.findViewById(R.id.DaeFragmentContainer);
                    spaceView.setVisibility(View.GONE);
                } else {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    DaeFragment childFragment = new DaeFragment();
                    fragmentTransaction.replace(R.id.DaeFragmentContainer, childFragment)
                            .setReorderingAllowed(true)
                            .addToBackStack("DAE")
                            .commit();
                    View spaceView = externalView.findViewById(R.id.DaeFragmentContainer);
                    spaceView.setVisibility(View.VISIBLE);
                }
            }
        });
        buttonChildpack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.ChildpackFragmentContainer);
                if (fragment != null && fragment.isVisible()) {
                    fragmentManager.beginTransaction().hide(fragment).commit();
                    View spaceView = externalView.findViewById(R.id.ChildpackFragmentContainer);
                    spaceView.setVisibility(View.GONE);
                } else {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    ChildpackFragment childFragment = new ChildpackFragment();
                    fragmentTransaction.replace(R.id.ChildpackFragmentContainer, childFragment)
                            .setReorderingAllowed(true)
                            .addToBackStack("Childpack")
                            .commit();
                    View spaceView = externalView.findViewById(R.id.ChildpackFragmentContainer);
                    spaceView.setVisibility(View.VISIBLE);
                }
            }
        });
        buttonMisc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.MiscFragmentContainer);
                if (fragment != null && fragment.isVisible()) {
                    fragmentManager.beginTransaction().hide(fragment).commit();
                    View spaceView = externalView.findViewById(R.id.MiscFragmentContainer);
                    spaceView.setVisibility(View.GONE);
                } else {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    MiscFragment childFragment = new MiscFragment();
                    fragmentTransaction.replace(R.id.MiscFragmentContainer, childFragment)
                            .setReorderingAllowed(true)
                            .addToBackStack("Misc")
                            .commit();
                    View spaceView = externalView.findViewById(R.id.MiscFragmentContainer);
                    spaceView.setVisibility(View.VISIBLE);
                }
            }
        });
        buttonDpi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.DpiFragmentContainer);
                if (fragment != null && fragment.isVisible()) {
                    fragmentManager.beginTransaction().hide(fragment).commit();
                    View spaceView = externalView.findViewById(R.id.DpiFragmentContainer);
                    spaceView.setVisibility(View.GONE);
                } else {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    DpiFragment childFragment = new DpiFragment();
                    fragmentTransaction.replace(R.id.DpiFragmentContainer, childFragment)
                            .setReorderingAllowed(true)
                            .addToBackStack("Dpi")
                            .commit();
                    View spaceView = externalView.findViewById(R.id.DpiFragmentContainer);
                    spaceView.setVisibility(View.VISIBLE);
                }
            }
        });
        buttonFrontspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.FrontspaceFragmentContainer);
                if (fragment != null && fragment.isVisible()) {
                    fragmentManager.beginTransaction().hide(fragment).commit();
                    View spaceView = externalView.findViewById(R.id.FrontspaceFragmentContainer);
                    spaceView.setVisibility(View.GONE);
                } else {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    FrontspaceFragment childFragment = new FrontspaceFragment();
                    fragmentTransaction.replace(R.id.FrontspaceFragmentContainer, childFragment)
                            .setReorderingAllowed(true)
                            .addToBackStack("Frontspace")
                            .commit();
                    View spaceView = externalView.findViewById(R.id.FrontspaceFragmentContainer);
                    spaceView.setVisibility(View.VISIBLE);
                }
            }
        });
        buttonTrauma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.TraumaFragmentContainer);
                if (fragment != null && fragment.isVisible()) {
                    fragmentManager.beginTransaction().hide(fragment).commit();
                    View spaceView = externalView.findViewById(R.id.TraumaFragmentContainer);
                    spaceView.setVisibility(View.GONE);
                } else {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    TraumaFragment childFragment = new TraumaFragment();
                    fragmentTransaction.replace(R.id.TraumaFragmentContainer, childFragment)
                            .setReorderingAllowed(true)
                            .addToBackStack("Trauma")
                            .commit();
                    View spaceView = externalView.findViewById(R.id.TraumaFragmentContainer);
                    spaceView.setVisibility(View.VISIBLE);
                }
            }
        });
        buttonBackpack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.BackpackFragmentContainer);
                if (fragment != null && fragment.isVisible()) {
                    fragmentManager.beginTransaction().hide(fragment).commit();
                    View spaceView = externalView.findViewById(R.id.BackpackFragmentContainer);
                    spaceView.setVisibility(View.GONE);
                } else {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    BackpackFragment childFragment = new BackpackFragment();
                    fragmentTransaction.replace(R.id.BackpackFragmentContainer, childFragment)
                            .setReorderingAllowed(true)
                            .addToBackStack("Backpack")
                            .commit();
                    View spaceView = externalView.findViewById(R.id.BackpackFragmentContainer);
                    spaceView.setVisibility(View.VISIBLE);
                }
            }
        });

        return externalView;
    }
}


