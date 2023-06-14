package com.example.gestionemagazzino.fragments;

// è il primo fragment che andrà dentro il container e in cui definisco i bottoni
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

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
        Button button = externalView.findViewById(R.id.B_mainspace);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, MainSpaceFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("mainSpace") // Name can be null
                        .commit();
            }
        });
        return externalView;
    }
}


/* class ButtonListener implements View.OnClickListener {

    // costruttore con parametri (forse lo togliamo perchè non serve)
  private Button button;
    public ButtonListener(Button button){
        this.button=button;

    }

    @Override
    public void onClick(View view) {
               switch (view.getId()) {
                    case R.id.B_mainspace:

                        break;

                    case R.id.B_aspiraz:


                        break;

                    case R.id.B_dae:

                        break;

                    case R.id.B_backpack:

                        break;

                    case R.id.B_childpack:

                        break;

                    case R.id.B_misc:

                        break;

                    case R.id.B_dpi:

                        break;

                    case R.id.B_trauma:

                        break;

                    case R.id.B_frontspace:

                        break;

                }
    }
}
*/
