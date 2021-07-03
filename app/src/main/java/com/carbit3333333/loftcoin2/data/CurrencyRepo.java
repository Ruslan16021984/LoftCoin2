package com.carbit3333333.loftcoin2.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

public interface CurrencyRepo {
    @NonNull
    LiveData<List<Currency>> availableCurrencys();

    @NonNull
    LiveData<Currency> currency();

    void updateCurrency(Currency currency);
}
