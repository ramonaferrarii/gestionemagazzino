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
import com.example.gestionemagazzino.models.FirebaseWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class TraumaFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private String[] dbKeys={"Barella Atraumatica","KED","Spinale", "Forbice Da Medicazione", "Steccobenda Avambraccio", "Steccobenda Gamba", "Steccobenda Braccio", "Pompetta"};

    private ArrayList<EditText> editTextsList = new ArrayList<EditText>();

    public TraumaFragment() {
        // Required empty public constructor
    }

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
        View externalView = inflater.inflate(R.layout.fragment_trauma, container, false);
        editTextsList.add(externalView.findViewById(R.id.ET_barella_atraumatica));
        editTextsList.add(externalView.findViewById(R.id.ET_KED));
        editTextsList.add(externalView.findViewById(R.id.ET_spinale));
        editTextsList.add(externalView.findViewById(R.id.ET_forbice_da_medicazione));
        editTextsList.add(externalView.findViewById(R.id.ET_steccobenda_avambraccio));
        editTextsList.add(externalView.findViewById(R.id.ET_steccobenda_braccio));
        editTextsList.add(externalView.findViewById(R.id.ET_steccobenda_gamba));
        editTextsList.add(externalView.findViewById(R.id.ET_pompetta));
        Button saveButton = externalView.findViewById(R.id.B_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Integer> editTextValues= new HashMap<>();
                for (int i = 0; i < dbKeys.length; i++) {
                    String text = editTextsList.get(i).getText().toString().trim();
                    if (!text.isEmpty()) {
                        int value = Integer.parseInt(text);
                        editTextValues.put(dbKeys[i], value);
                    } else {
                        CharSequence msg = "Il form non può contenere elementi vuoti";
                        Toast.makeText(externalView.getContext(), msg, Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                FirebaseWrapper.RTDatabase RTdb = new FirebaseWrapper.RTDatabase();
                boolean count=true;
                for (Map.Entry<String, Integer> entry : editTextValues.entrySet()){
                    RTdb.updateDbData("Trauma",entry.getKey(),entry.getValue(),getContext(),count);
                    count = false;
                }
                for (EditText editText : editTextsList)
                    editText.setText("0");

            }
        });
        return externalView;
    }
}