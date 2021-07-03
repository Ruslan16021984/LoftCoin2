package com.carbit3333333.loftcoin2;

import android.app.Application;
import android.os.StrictMode;

import com.carbit3333333.loftcoin2.util.DebugTree;

import timber.log.Timber;

public class LoftApp extends Application {
    private BaseComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG){
            StrictMode.enableDefaults();
            Timber.plant(new DebugTree());
        }
        appComponent = DaggerAppComponent.builder().application(this).build();
    }

    public BaseComponent getAppComponent() {
        return appComponent;
    }
}
