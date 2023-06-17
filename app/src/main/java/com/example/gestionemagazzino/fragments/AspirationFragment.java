package com.example.gestionemagazzino.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gestionemagazzino.R;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AspirationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AspirationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText asp ;
    private EditText raccCo;
    private EditText saccMon;
    private EditText tubRacc;




    public AspirationFragment() {
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
    public static AspirationFragment newInstance(String param1, String param2) {
        AspirationFragment fragment = new AspirationFragment();
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
        View externalView = inflater.inflate(R.layout.fragment_aspiration, container, false);
        asp=externalView.findViewById(R.id.ET_aspiratore);
        raccCo= externalView.findViewById(R.id.ET_raccordo_conico);
        saccMon= externalView.findViewById(R.id.ET_sacchetti_monouso);
        tubRacc= externalView.findViewById(R.id.ET_tuboRaccordo);
        Button saveButton = externalView.findViewById(R.id.B_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Integer> editTextValues= new HashMap<>();
                editTextValues.put("Aspiratore", Integer.parseInt(asp.getText().toString()));
                editTextValues.put("RaccConic", Integer.parseInt(raccCo.getText().toString()));
                editTextValues.put("SacchMono", Integer.parseInt(saccMon.getText().toString()));
                editTextValues.put("TuboRacc", Integer.parseInt(asp.getText().toString()));
                CharSequence msg="parametri salvati";
                Toast.makeText(externalView.getContext(), msg, Toast.LENGTH_SHORT).show();
                //TODO: handle the case where the user submits an empty editText
            }
        });
        return externalView;
    }
}