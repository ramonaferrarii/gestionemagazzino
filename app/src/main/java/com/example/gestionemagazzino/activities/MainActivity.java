package com.example.gestionemagazzino.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.gestionemagazzino.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button B_mainSpace = findViewById(R.id.B_mainspace);
        Button B_aspiraz = findViewById(R.id.B_aspiraz);
        Button B_dae = findViewById((R.id.B_dae));
        LinearLayout buttonsContainer = findViewById(R.id.buttonsContainer);
        Animation slideAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_down);
        slideAnimation.setFillAfter(true);
        ButtonListener buttonListener = new ButtonListener(buttonsContainer, slideAnimation);

        B_mainSpace.setOnClickListener(buttonListener);
        B_aspiraz.setOnClickListener(buttonListener);
    }





}

    class ButtonListener implements View.OnClickListener {
        private LinearLayout buttonsContainer;
        private Animation slideAnimation;
        // definisco listener
        // private View.OnClickListener buttonListener = new View.OnClickListener() {
        public ButtonListener(LinearLayout buttonsContainer, Animation slideAnimation){
            this.buttonsContainer=buttonsContainer;
            this.slideAnimation=slideAnimation;
        }




            @Override
            public void onClick(View view) {

                switch (view.getId()) {
                    case R.id.B_mainspace:

                        //the cycle shifts every button down
                        for (int i = 1; i < buttonsContainer.getChildCount(); i++) {
                            View button = buttonsContainer.getChildAt(i);
                            button.startAnimation(slideAnimation);
                        }


                        break;

                    case R.id.B_aspiraz:

                        for (int i = 2; i < buttonsContainer.getChildCount(); i++) {
                            View button = buttonsContainer.getChildAt(i);
                            button.startAnimation(slideAnimation);
                        }


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

            ;
        };






