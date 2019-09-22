package com.example.assignment2.activity.activity.activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.assignment2.R;

public class SplashActivity extends AppCompatActivity {
    private TextView tvAsignment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initComponent();
        splash();
    }

    private void initComponent() {

        tvAsignment=findViewById(R.id.tv_assignment);
    }
    private void splash() {
        Thread thread=new Thread()
        {
            public void run()
        {
                try{
                    sleep(3000);
                    Intent intent=new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                }catch (Exception e){}

        }

        };
        thread.start();
    }
}
