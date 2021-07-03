package com.carbit3333333.loftcoin2.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.carbit3333333.loftcoin2.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public
class CurrencyRepoImpl implements CurrencyRepo {
    private final Context context;

    @Inject
    CurrencyRepoImpl(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public LiveData<List<Currency>> availableCurrencys() {
        return new AllCurrencysLiveData(context);
    }

    @NonNull
    @Override
    public LiveData<Currency> currency() {
        return null;
    }

    @Override
    public void updateCurrency(Currency currency) {

    }
    private static class AllCurrencysLiveData extends LiveData<List<Currency>>{

        private Context context;

        AllCurrencysLiveData(Context context) {

            this.context = context;
        }

        @Override
        protected void onActive() {

            List<Currency> currencies = new ArrayList<>();
            currencies.add(Currency.create("$", "USD", context.getString(R.string.usd)));
            currencies.add(Currency.create("E", "USD", context.getString(R.string.eur)));
            currencies.add(Currency.create("R", "USD", context.getString(R.string.rub)));
            setValue(currencies);
        }

        @Override
        protected void onInactive() {
            super.onInactive();
        }
    }
}
