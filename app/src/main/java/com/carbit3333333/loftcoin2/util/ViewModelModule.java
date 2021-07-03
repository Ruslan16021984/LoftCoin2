package com.carbit3333333.loftcoin2.util;

import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory viewModelFactory(VIewModelFactory impl);
}
