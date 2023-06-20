package com.example.gestionemagazzino.models;

import android.annotation.SuppressLint;
import  android.content.Context;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.gestionemagazzino.R;
import com.example.gestionemagazzino.activities.MainActivity;

import java.security.Permission;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MyBackgroundWorker extends Worker{

    private static final int PERMISSION_REQUEST_CODE=(new Random()).nextInt() & Integer.MAX_VALUE;
    public MyBackgroundWorker(@NonNull Context context,@NonNull WorkerParameters workerParams){
        super(context,workerParams);
    }

    @NonNull
    @Override
    public Result doWork(){
        //TODO: extend to every field, now just for testing purposes we are using only the "Aspirazione" document
        FirebaseWrapper.RTDatabase db = new FirebaseWrapper.RTDatabase();
        db.readDbData("Aspirazione", new FirebaseWrapper.RTDatabase.FirestoreCallback() {
            @Override
            public void onCallback(HashMap<String, Object> data) {
                if (data != null) {
                    //check every entry in the desired document for quantities inferior to 5
                    for (Map.Entry<String, Object> entry : data.entrySet()) {
                        if ((Long)entry.getValue()<5){
                            String obj=entry.getKey();
                             //notification function
                            SendNotification("oggetto in esaurimento: "+obj);
                        }
                    }
                }
            }
        });
        //TODO: to be redefined
        return Result.success();
    }

    @SuppressLint("MissingPermission")
    private void SendNotification(String msg){
        //TODO: find way to obtain channel_id
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "GMNC1")
                .setContentTitle("Materiale in esaurimento")
                .setContentText(msg)
                .setSmallIcon(R.drawable.ic_stat_onesignal_default)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

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
