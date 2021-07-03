package com.carbit3333333.loftcoin2.ui.rates;

import androidx.lifecycle.ViewModelProvider;

import com.carbit3333333.loftcoin2.BaseComponent;
import com.carbit3333333.loftcoin2.util.ViewModelModule;

import javax.inject.Singleton;

import dagger.Component;
@Singleton
@Component(modules = {
        RatesModule.class,
        ViewModelModule.class
}, dependencies = {
        BaseComponent.class
})
abstract class RatesComponent {

    abstract ViewModelProvider.Factory viewModelFactory();

}
