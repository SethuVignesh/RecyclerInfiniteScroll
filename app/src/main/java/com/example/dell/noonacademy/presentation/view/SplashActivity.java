package com.example.dell.noonacademy.presentation.view;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dell.noonacademy.R;
import com.example.dell.noonacademy.data.RepositoryImpl;

public class SplashActivity extends AppCompatActivity {
    public Context context = SplashActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MovieListActivity.class);
                intent.putExtra("goto", "loginActivity");
                startActivity(intent);
                finish();

            }
        }, 2000);
    }
}
