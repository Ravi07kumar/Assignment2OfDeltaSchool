package com.example.assignment2.activity.activity.activity.utils;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class CommanUtil {
    public  static void showSnackbar(View view,String message){

        Snackbar.make(view,message,Snackbar.LENGTH_SHORT).show();
    }
}
