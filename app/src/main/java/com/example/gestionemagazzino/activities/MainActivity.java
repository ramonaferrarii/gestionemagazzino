package com.example.gestionemagazzino.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.work.Constraints;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.gestionemagazzino.R;
import com.example.gestionemagazzino.fragments.ButtonsFragment;
import com.example.gestionemagazzino.fragments.HelpFragment;
import com.example.gestionemagazzino.fragments.MyProfileFragment;
import com.example.gestionemagazzino.models.MyBackgroundWorker;
import com.example.gestionemagazzino.models.PermissionManager;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //notification channel creation
        createNotificationChannel();
        //background worker init
        Constraints constraints = new Constraints.Builder().build();
        PeriodicWorkRequest workRequest = new PeriodicWorkRequest
                .Builder(MyBackgroundWorker.class, 15, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build();
        WorkManager.getInstance(this).enqueue(workRequest);

         // definzione di ActionBar che mi serve per il tasto per tornare indietro
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        // devo definire la chiamata al ButtonsFragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fragment_container_view, ButtonsFragment.class, null)
                .setReorderingAllowed(true)
                //non aggiungo questo fragment alla backstack, altrimenti
                //cliccando il tasto indietro potrei cancellare il fragment dei bottoni
                .commit();


    }
    //Function for creation of notification channel must be called ad soon as the app starts
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("GMNC1", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

// per il tasto indietro: devo controllare dove sono e se il fragment che visualizzo è MyProfile oppure Help
    // devo sostituirlo con quello dei bottoni. in tutti gli altri casi va bene la funzione che svolge onBackPressed
    @Override public void onBackPressed() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container_view);
        if (currentFragment instanceof MyProfileFragment || currentFragment instanceof HelpFragment) {
            Fragment buttonsFragment = new ButtonsFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container_view, buttonsFragment);
            transaction.commit();
        } else {
            super.onBackPressed(); }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.myprofile:
            //azione da eseguire su MyProfile
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container_view, new MyProfileFragment());
                fragmentTransaction.commit();
                return true;
            case R.id.help:
            //azione da eseguire su Help
                FragmentManager fragmentManager2 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                fragmentTransaction2.replace(R.id.fragment_container_view, new HelpFragment());
                fragmentTransaction2.commit();
                return true;
            case android.R.id.home:
                // tasto per tornare indietro
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }




    }


/*public void OnClick(View v) {
        switch(v.getId()){
            case R.id.myprofile:
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container_view, new MyProfileFragment());
                fragmentTransaction.commit();
                break;

            case R.id.help:


                break;


        }
}*/

}









