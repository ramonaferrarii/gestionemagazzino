package com.example.gestionemagazzino.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.gestionemagazzino.R;
import com.example.gestionemagazzino.models.FirebaseWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DaeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private String[] dbKeys={"dae","Garze Non Sterili","Piastre Monouso", "Rasoio"};

    private ArrayList<EditText> editTextsList = new ArrayList<EditText>();


    public DaeFragment() {
        // Required empty public constructor
    }
    public static DaeFragment newInstance(String param1, String param2) {
        DaeFragment fragment = new DaeFragment();
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

        View externalView = inflater.inflate(R.layout.fragment_dae, container, false);
        editTextsList.add(externalView.findViewById(R.id.ET_dae));
        editTextsList.add(externalView.findViewById(R.id.ET_garze_non_sterili));
        editTextsList.add(externalView.findViewById(R.id.ET_piastre_monouso));
        editTextsList.add(externalView.findViewById(R.id.ET_rasoio));
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
                for (Map.Entry<String, Integer> entry : editTextValues.entrySet()){
                    RTdb.updateDbData("Defibrillatore",entry.getKey(),entry.getValue());
                }
                for (EditText editText : editTextsList)
                    editText.setText("0");
                CharSequence msg="parametri salvati";
                Toast.makeText(externalView.getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
        return externalView;
    }
}