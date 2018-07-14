package com.example.dell.noonacademy.data.cache;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class PrefUtils {

    private static final String FAV_LIST = "fav";
    public static String PREF_FILE_NAME = "noon_academy_pref";

    public synchronized static void saveFavList(ArrayList<String> bookMarked, @NonNull Context context) {

        SharedPreferences preferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefrencesEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(bookMarked);
        prefrencesEditor.putString(FAV_LIST, json);
        prefrencesEditor.commit();
    }

    public static synchronized ArrayList<String> getFavList(@NonNull Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String json = preferences.getString(FAV_LIST, null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        ArrayList<String> arrayList = gson.fromJson(json, type);
        if (arrayList == null) return new ArrayList<>();
        return arrayList;
    }
}
