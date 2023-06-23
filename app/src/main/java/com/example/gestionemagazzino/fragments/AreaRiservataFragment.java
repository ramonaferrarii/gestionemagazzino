package com.example.gestionemagazzino.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.example.gestionemagazzino.models.FirebaseWrapper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestionemagazzino.R;
import com.example.gestionemagazzino.adapters.ItemAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AreaRiservataFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private ArrayList<String> itemList;
    private ItemAdapter adapter;

    public AreaRiservataFragment() {
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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_area_riservata, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        FirebaseWrapper.RTDatabase db= new FirebaseWrapper.RTDatabase();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        itemList = new ArrayList<>();
        db.readDbData(new FirebaseWrapper.RTDatabase.FirestoreCallback() {
            @Override
            public void onCallback(HashMap<String, Object> data) {
                if(data!=null){
                    for(Map.Entry<String,Object> entry : data.entrySet())
                        itemList.add(entry.getKey()+" : "+entry.getValue());
                    adapter = new ItemAdapter(itemList);
                    recyclerView.setAdapter(adapter);
                }
            }
        });

        return view;
    }


}
