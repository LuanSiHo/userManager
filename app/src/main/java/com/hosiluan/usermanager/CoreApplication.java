package com.hosiluan.usermanager;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by User on 11/16/2017.
 */

public class CoreApplication extends Application {

    private static CoreApplication sApplication;

    public CoreApplication() {
    }

    public static  CoreApplication getInstance(){
        return sApplication;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
