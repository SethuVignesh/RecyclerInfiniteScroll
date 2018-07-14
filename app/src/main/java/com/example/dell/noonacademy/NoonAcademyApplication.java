package com.example.dell.noonacademy;

import android.app.Application;
import android.content.Context;

import com.example.dell.noonacademy.data.Repository;
import com.example.dell.noonacademy.data.RepositoryImpl;

public class NoonAcademyApplication extends Application {
    static Context context;

    @Override
    public void onCreate() {
        context = NoonAcademyApplication.this;
        super.onCreate();
    }

    public static Repository repository = new RepositoryImpl(context);
}
