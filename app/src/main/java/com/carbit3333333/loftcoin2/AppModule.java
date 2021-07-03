package com.carbit3333333.loftcoin2;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class AppModule {

    @Singleton
    @Provides
    static Context context(Application application){
        return application.getApplicationContext();
    }
}
