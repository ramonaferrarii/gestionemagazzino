package com.example.gestionemagazzino.models;

import android.annotation.SuppressLint;
import  android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.gestionemagazzino.R;
import com.example.gestionemagazzino.activities.MainActivity;

import java.security.Permission;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class MyBackgroundWorker extends Worker{

    private static final String[] docs = {"Aspirazione","DPI", "Defibrillatore", "KitAmbu", "Trauma", "Ustione", "Vano Guida", "Varie", "ZainoPediatrico", "medicazione", "parto"};

    private static final int PERMISSION_REQUEST_CODE=(new Random()).nextInt() & Integer.MAX_VALUE;

    private final static String TAG = MyBackgroundWorker.class.getCanonicalName();

    private Semaphore semaphore;

    private ArrayList<String> objs = new ArrayList<>();
    public MyBackgroundWorker(@NonNull Context context,@NonNull WorkerParameters workerParams){
        super(context,workerParams);
    }

    @NonNull
    @Override
    public Result doWork(){

        FirebaseWrapper.RTDatabase db = new FirebaseWrapper.RTDatabase();
        for(int i=0; i<docs.length; i++) {
            db.readDbData(docs[i], new FirebaseWrapper.RTDatabase.FirestoreCallback() {
                @Override
                public void onCallback(HashMap<String, Object> data) {

                    if (data != null)
                        //check every entry in the desired document for quantities inferior to 5
                        for (HashMap.Entry<String, Object> entry : data.entrySet())
                            if ((Long)entry.getValue() < 5) {
                                objs.add(entry.getKey());

                            }
                    String obj= String.join(", ",objs);
                    //notification function
                    if(!objs.isEmpty())
                        SendNotification("oggetto in esaurimento: "+obj);
                }
            });
            //TODO: to be redefined
        }


        return Result.success();
    }

    @SuppressLint("MissingPermission")
    private void SendNotification(String msg){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "GMNC1")
                .setContentTitle("Materiale in esaurimento")
                .setContentText(msg)
                .setSmallIcon(R.drawable.ic_stat_onesignal_default)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg));

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
        /*MainActivity main = (MainActivity)getApplicationContext();
        PermissionManager pm = new PermissionManager(main);
        if(!pm.askNeededPermissions(PERMISSION_REQUEST_CODE,false))*/
            //the missing permission should be "build dependent" this means that a system
            //with android build < 33, shouldn't have the permission POST_NOTIFICATIONS in their
            //needed_permission array
            notificationManager.notify(1,builder.build());
    }


}
