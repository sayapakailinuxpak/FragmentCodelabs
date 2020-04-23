package com.eldisprojectcodelabs.fragmentcodelabs;

import android.app.Application;
import android.content.Context;

public class GlobalApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        /*if you has other classes that need context object to initialize when application is created,
        you can use the context here to process */

    }

    public static Context getGlobalContext(){
        return context;
    }
}
