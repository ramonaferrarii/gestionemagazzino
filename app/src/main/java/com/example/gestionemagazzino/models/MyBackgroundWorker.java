package com.example.gestionemagazzino.models;

import  android.content.Context;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.HashMap;
import java.util.Map;

public class MyBackgroundWorker extends Worker{

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
                    for (Map.Entry<String, Object> entry : data.entrySet()) {
                        if ((Integer)entry.getValue()<5){
                        //TODO: implement notification function
                        }
                    }
                }
            }
        });
    }

    private void SendNotification(String msg){
        //TODO: find way to obtain channel_id
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "channelid")
    }


}
