package com.carbit3333333.loftcoin2.ui.main;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import com.carbit3333333.loftcoin2.ui.converter.ConverterFragment;
import com.carbit3333333.loftcoin2.ui.currency.CurrencyDialog;
import com.carbit3333333.loftcoin2.ui.rates.RatesFragment;
import com.carbit3333333.loftcoin2.ui.wallets.WalletFragment;
import com.carbit3333333.loftcoin2.util.LoftFragmentFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module
abstract class MainModule {

    @Binds
    abstract FragmentFactory fragmentFactory(LoftFragmentFactory impl);

    @Binds
    @IntoMap
    @ClassKey(RatesFragment.class)
    abstract Fragment ratesFragment(RatesFragment impl);

    @Binds
    @IntoMap
    @ClassKey(WalletFragment.class)
    abstract Fragment walletsFragment(WalletFragment impl);

    @Binds
    @IntoMap
    @ClassKey(ConverterFragment.class)
    abstract Fragment converterFragment(ConverterFragment impl);

//    @Binds
//    @IntoMap
//    @ClassKey(CurrencyDialog.class)
//    abstract Fragment currencyDialog(CurrencyDialog impl);

}