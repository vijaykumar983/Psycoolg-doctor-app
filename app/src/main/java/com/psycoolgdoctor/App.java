package com.psycoolgdoctor;

import android.app.Application;


public class App extends Application {
    private App singleton = null;

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
    }
}