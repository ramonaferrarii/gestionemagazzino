package com.example.gestionemagazzino.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gestionemagazzino.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        }

    ButtonListener buttonListener = new ButtonListener();
}

    class ButtonListener implements View.OnClickListener {
        // definisco listener
        // private View.OnClickListener buttonListener = new View.OnClickListener() {

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
            };
        };






