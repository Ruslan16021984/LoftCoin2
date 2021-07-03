package com.carbit3333333.loftcoin2.ui.rates;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.carbit3333333.loftcoin2.data.CmcCoinsRepo;
import com.carbit3333333.loftcoin2.data.Coin;
import com.carbit3333333.loftcoin2.data.CoinsRepo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.inject.Inject;

import dagger.Module;


public class RatesViewModel extends ViewModel {
    private final MutableLiveData<List<Coin>> coins = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isRefreshing = new MutableLiveData<>();

    @NonNull
    public LiveData<Boolean> ssRefreshing() {
        return isRefreshing;
    }

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private Future<?> future;

    private final CoinsRepo repo;

    @NonNull
    public LiveData<List<Coin>> coins() {
        return coins;
    }

    @Inject
    public RatesViewModel(CoinsRepo coinsRepo) {
        this.repo = coinsRepo;
        refresh();
    }

    final void refresh() {
        isRefreshing.postValue(true);
        future = executor.submit(() -> {
            try {
                coins.postValue(new ArrayList<>(repo.listings("USD")));
                isRefreshing.postValue(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (future != null) {
            future.cancel(true);
        }
    }
}
