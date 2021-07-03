package com.carbit3333333.loftcoin2;

import android.content.Context;

import com.carbit3333333.loftcoin2.data.CoinsRepo;
import com.carbit3333333.loftcoin2.data.CurrencyRepo;

public interface BaseComponent {
     Context context();
     CoinsRepo coinsRepo();
     CurrencyRepo currencyRepo();
}
