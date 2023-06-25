package com.example.gestionemagazzino.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gestionemagazzino.models.FirebaseWrapper;

import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestionemagazzino.R;
import com.example.gestionemagazzino.adapters.ItemAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AreaRiservataFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private List<String> objectList;

    private final String TAG = AreaRiservataFragment.class.getCanonicalName();

    private RecyclerView recyclerView;
    private ArrayList<String> itemList;
    private ItemAdapter adapter;

    public AreaRiservataFragment() {
        // Required empty public constructor
    }

    public static AreaRiservataFragment newInstance(String param1, String param2) {
        AreaRiservataFragment fragment = new AreaRiservataFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_area_riservata, container, false);
        //Recycler view
        recyclerView = view.findViewById(R.id.recycler_view);
        FirebaseWrapper.RTDatabase db= new FirebaseWrapper.RTDatabase();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        itemList = new ArrayList<>();
        //UI elements for storage replenishing
        Button button = view.findViewById(R.id.B_reintegro);
        EditText editText = view.findViewById(R.id.ET_reintegro);
        TextInputLayout textInputLayout = view.findViewById(R.id.textInputLayout);
        AppCompatAutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.autoCompleteTextView);
        objectList = new ArrayList<>();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check for missing fields
                if (!editText.getText().toString().isEmpty() && !autoCompleteTextView.getText().toString().isEmpty()){
                    db.updateDbByField(autoCompleteTextView.getText().toString(),Integer.parseInt(editText.getText().toString()),getContext());
                    // Once sent, the value is reset, in order to prevent accidental multiple updates
                    editText.setText("0");
                    // Refresh the items list with the updated values
                    itemList.clear();
                    db.readDbData(new FirebaseWrapper.RTDatabase.FirestoreCallback() {
                        @Override
                        public void onCallback(HashMap<String, Object> data) {
                            if(data!=null){
                                for(Map.Entry<String,Object> entry : data.entrySet()){
                                    // For complete list of key-value pairs
                                    itemList.add(entry.getKey()+" : "+entry.getValue());
                                }

                            }
                            // Recycler view
                            adapter = new ItemAdapter(itemList);
                            recyclerView.setAdapter(adapter);

                        }
                    });

                }else
                    Toast.makeText(view.getContext(), "i campi non possono essere lasciati vuoti", Toast.LENGTH_SHORT).show();


            }
        });

        db.readDbData(new FirebaseWrapper.RTDatabase.FirestoreCallback() {
            @Override
            public void onCallback(HashMap<String, Object> data) {
                if(data!=null){
                    for(Map.Entry<String,Object> entry : data.entrySet()){
                        //for complete list of key-value pairs
                        itemList.add(entry.getKey()+" : "+entry.getValue());
                        //for complete list of object in db for quantity updates (not to be reset)
                        objectList.add((entry.getKey()));
                    }

                }
                // Recycler view
                adapter = new ItemAdapter(itemList);
                recyclerView.setAdapter(adapter);

                // Storage update utilities

                String[] objectArray = new String[objectList.size()];
                objectArray = objectList.toArray(objectArray);

                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_dropdown_item_1line,objectArray);
                autoCompleteTextView.setAdapter(adapter);
                autoCompleteTextView.setThreshold(1); // Shows suggestions after first char
                autoCompleteTextView.setTextColor(Color.WHITE);
            }
        });

        return view;
    }

}
