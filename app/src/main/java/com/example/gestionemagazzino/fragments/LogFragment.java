package com.example.gestionemagazzino.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class LogFragment extends Fragment{
    private static final String ARG_PARAM1 = "method_name";
    private static final String ARG_PARAM2 = "method_prms";

    protected String callbackName;
    protected Class[] callbackPrms;

    public static LogFragment newInstance(Class<? extends LogFragment> clazz, String param1, Class<?>... prms) {
        LogFragment instance = null;
        try {
            Constructor<?> constructor = clazz.getConstructor();
            instance = (LogFragment) constructor.newInstance();
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException |
                 java.lang.InstantiationException e) {
            throw new RuntimeException(e);
        }

        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putSerializable(ARG_PARAM2, prms);
        instance.setArguments(args);

        return instance;
    }
    protected void initArguments() {
        if (getArguments() != null) {
            this.callbackName = getArguments().getString(ARG_PARAM1);
            this.callbackPrms = (Class[]) getArguments().getSerializable(ARG_PARAM2);
  }
}

}


