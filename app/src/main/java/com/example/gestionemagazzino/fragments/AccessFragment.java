package com.example.gestionemagazzino.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gestionemagazzino.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccessFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccessFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccessFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccessFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccessFragment newInstance(String param1, String param2) {
        AccessFragment fragment = new AccessFragment();
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
        View externalView= inflater.inflate(R.layout.fragment_access, container, false);
        EditText editText = externalView.findViewById(R.id.ET_Pass);
        Button button = externalView.findViewById(R.id.B_ENTER_AR);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //password hardcodata solo a fini didattici, la versione finale dovrebbe conservare la password in remoto
                String pass="admin";
                if(editText.getText().toString().equals(pass)){
                    AreaRiservataFragment fragment = new AreaRiservataFragment();
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction
                            .replace(R.id.AccessFragmentContainer, fragment)
                            .addToBackStack("AreaRiservata")
                            .commit();

                }
                else
                    Toast.makeText(externalView.getContext(), "password incorretta", Toast.LENGTH_SHORT).show();

            }
        });
        return externalView;
    }
}