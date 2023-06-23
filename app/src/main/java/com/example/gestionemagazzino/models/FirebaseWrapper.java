
package com.example.gestionemagazzino.models;


import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.C;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

// NOTE: With firebase we have to do a network request --> We need to add the permission in the AndroidManifest.xml
//      -> ref: https://developer.android.com/training/basics/network-ops/connecting

// Firebase auth - https://firebase.google.com/docs/auth/android/start?hl=en#java
// Firebase db - https://firebase.google.com/docs/database/android/start?hl=en

// 1) Create a new project from - https://firebase.google.com/ (console: https://console.firebase.google.com/u/0/)
// 2) Enable authentication: Build > Authentication > Get started , then enable Email/password (or other auth types)
// 3a) In Android Studio: Tools > Firebase > Authentication (or Realtime Database or the thing that you need!)
//      ( Then follow the instructions )
// 3b) Alternative you can connect firebase to your Android app - https://firebase.google.com/docs/android/setup?hl=en#register-app


    public class FirebaseWrapper {

        public static class Callback {
            private final static String TAG = Callback.class.getCanonicalName();
            private final Method method;
            private final Object thiz;



            public Callback(Method method, Object thiz) {
                this.method = method;
                this.thiz = thiz;
            }

            public static Callback newInstance(Object thiz, String name, Class<?>... prms) {
                Class<?> clazz = thiz.getClass();
                try {
                    return new Callback(clazz.getMethod(name, prms), thiz);
                } catch (NoSuchMethodException e) {
                    Log.w(TAG, "Cannot find method " + name + " in class " + clazz.getCanonicalName());

                    // TODO: Better handling of the error
                    throw new RuntimeException(e);
                }
            }

            public void invoke(Object... objs) {
                try {
                    this.method.invoke(thiz, objs);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    Log.w(TAG, "Something went wrong during the callback. Message: " + e.getMessage());

                    // TODO: Better handling of such an error
                    throw new RuntimeException(e);
                }
            }
        }

        // Auth with email and password: https://firebase.google.com/docs/auth/android/password-auth?hl=en
        public static class Auth {
            private final static String TAG = Auth.class.getCanonicalName();
            private final FirebaseAuth auth;

            public Auth() {
                this.auth = FirebaseAuth.getInstance();
            }

            public boolean isAuthenticated() {
                return this.auth.getCurrentUser() != null;
            }

            public FirebaseUser getUser() {
                return this.auth.getCurrentUser();
            }

            public void signOut() {
                this.auth.signOut();
            }

            public void signIn(String email, String password, FirebaseWrapper.Callback callback) {
                this.auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                callback.invoke(task.isSuccessful());
                            }
                        });
            }

            public void signUp(String email, String password, FirebaseWrapper.Callback callback) {
                this.auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                callback.invoke(task.isSuccessful());
                            }
                        });
            }
        }

        public static class RTDatabase{
            private final static String TAG = RTDatabase.class.getCanonicalName();

            private static final String CHILD = "Magazzino";

            public HashMap<String, Object> data;

            private FirebaseFirestore getDb(){
                FirebaseFirestore ref =
                        FirebaseFirestore.getInstance();


                return ref;
            }


            public void updateDbData(String doc, String key, int value){
                //get reference to specified document
                DocumentReference docRef = getDb().collection(CHILD).document(doc);
                docRef
                        .update(key, FieldValue.increment(-value))
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d(TAG, "DocumentSnapshot successfully updated");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error updating document", e);
                            }
                        });

            }

            public void readDbData( FirestoreCallback callback){
                FirebaseFirestore db = getDb();


                db.collection(CHILD).get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        data=new HashMap<String,Object>();
                        data.clear();
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                                Map<String,Object> tempData = document.getData();
                                assert tempData != null;
                                data.putAll(tempData);

                            }
                            callback.onCallback(data);
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });

            }

            public interface FirestoreCallback{
                void onCallback (HashMap<String, Object> data);
            }

        }

    }




