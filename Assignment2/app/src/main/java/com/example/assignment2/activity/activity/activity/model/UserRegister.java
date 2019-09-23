package com.example.assignment2.activity.activity.activity.model;

import java.util.Date;

public class UserRegister {
    private String mName;
    private String mEmail;
    private String mPassword;
    private String mGender;
    private String mDob;
    private String mUserType;
    private String mOccupation;


    public UserRegister(String mName, String mEmail, String mPassword, String mGender, String mDob, String mUserType, String mOccupation) {
        this.mName = mName;
        this.mEmail = mEmail;
        this.mPassword = mPassword;
        this.mGender = mGender;
        this.mDob = mDob;
        this.mUserType = mUserType;
        this.mOccupation = mOccupation;
    }

    public String getmName() {
        return mName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public String getmPassword() {
        return mPassword;
    }

    public String getmGender() {
        return mGender;
    }

    public String getmDob() {
        return mDob;
    }

    public String getmUserType() {
        return mUserType;
    }

    public String getmOccupation() {
        return mOccupation;
    }
}
