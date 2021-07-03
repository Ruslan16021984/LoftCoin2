package com.carbit3333333.loftcoin2.ui.main;

import com.carbit3333333.loftcoin2.BaseComponent;

import javax.inject.Singleton;

import dagger.Component;
@Singleton
@Component(modules = {
        MainModule.class
}, dependencies = {
        BaseComponent.class
})
public abstract class MainComponent {

   abstract void inject(MainActivity activity);

}
