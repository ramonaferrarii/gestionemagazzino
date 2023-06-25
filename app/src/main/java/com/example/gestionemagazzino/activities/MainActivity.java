package com.example.gestionemagazzino.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.work.Constraints;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;


import android.app.NotificationChannel;
import android.app.NotificationManager;


import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.example.gestionemagazzino.R;
import com.example.gestionemagazzino.fragments.ButtonsFragment;
import com.example.gestionemagazzino.fragments.HelpFragment;
import com.example.gestionemagazzino.fragments.MyProfileFragment;
import com.example.gestionemagazzino.models.MyBackgroundWorker;


import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Notification channel creation
        createNotificationChannel();
        //background worker init
        Constraints constraints = new Constraints.Builder().build();
        PeriodicWorkRequest workRequest = new PeriodicWorkRequest
                .Builder(MyBackgroundWorker.class, 15, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build();
        WorkManager.getInstance(this).enqueue(workRequest);

         // Definition of ActionBar (needed by Back Button)
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fragment_container_view, ButtonsFragment.class, null)
                .setReorderingAllowed(true)
                .commit();


    }
    // Function for creation of notification channel must be called ad soon as the app starts
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

// For Back Button: if the visible fragment is MyProfileFragment or HelpFragment we have to substitute it with ButtonsFragment
    // In other cases we use onBackPressed
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
            // Action to do on MyProfile
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container_view, new MyProfileFragment());
                fragmentTransaction.commit();
                return true;
            case R.id.help:
            //Action to do on Help
                FragmentManager fragmentManager2 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                fragmentTransaction2.replace(R.id.fragment_container_view, new HelpFragment());
                fragmentTransaction2.commit();
                return true;
            case android.R.id.home:
                // Action to do on Back Button
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}









