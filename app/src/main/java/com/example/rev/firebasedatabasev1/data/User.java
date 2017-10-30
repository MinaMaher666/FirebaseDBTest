package com.example.rev.firebasedatabasev1.data;

public class User {
    private String mUserName;
    private String mPassword;

    public User () {
    }

    public User(String mUserName, String mPassword) {
        this.mUserName = mUserName;
        this.mPassword = mPassword;
    }

    public String getmUserName() {
        return mUserName;
    }

    public String getmPassword() {
        return mPassword;
    }
}
