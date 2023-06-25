
package com.example.gestionemagazzino.models;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;



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
                    throw new RuntimeException(e);
                }
            }

            public void invoke(Object... objs) {
                try {
                    this.method.invoke(thiz, objs);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    Log.w(TAG, "Something went wrong during the callback. Message: " + e.getMessage());
                    throw new RuntimeException(e);
                }
            }
        }
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


            public void updateDbData(String doc, String key, int value, Context context,boolean count){
                // Get reference to specified document
               DocumentReference docRef = getDb().collection(CHILD).document(doc);
                docRef
                        .update(key, FieldValue.increment(-value))
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d(TAG, "DocumentSnapshot successfully updated");
                                if(count)
                                    Toast.makeText(context,"Magazzino correttamente aggiornato", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error updating document", e);
                                Toast.makeText(context,"Errore nell'aggiornamento, si prega di riprovare",Toast.LENGTH_SHORT).show();
                            }
                        });

            }

            //Necessary for updating fields without knowing the document name, this simplifies user experience
            public void updateDbByField(String fieldName, int incrementValue,Context context){
                FirebaseFirestore db = getDb();
                CollectionReference collectionRef=db.collection(CHILD);
                collectionRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                String documentId = document.getId();
                                Map<String,Object> tempData = document.getData();
                                assert tempData != null;
                                for (Map.Entry<String, Object> entry : tempData.entrySet())
                                    if (Objects.equals(entry.getKey(), fieldName)) {
                                        entry.setValue(Integer.parseInt(entry.getValue().toString()) + incrementValue);
                                    }


                                collectionRef.document(documentId).update(tempData)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    //message displayed n-times (once for every document), couldn't find a solution
                                                    Log.d(TAG,"Aggiornamento completato con successo per il documento: " + documentId);
                                                    Toast.makeText(context, "Magazzino correttamente aggiornato", Toast.LENGTH_SHORT).show();

                                                } else {
                                                    Log.d(TAG,"Si è verificato un errore durante l'aggiornamento del documento: " + documentId);
                                                    Toast.makeText(context,"Errore nell'aggiornamento, si prega di riprovare",Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }
                        } else {
                           Log.d(TAG,"Si è verificato un errore durante la ricerca dei documenti: " + task.getException());
                        }
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




