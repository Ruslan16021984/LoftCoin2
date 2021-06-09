package com.carbit3333333.loftcoin2.ui.rates;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.carbit3333333.loftcoin2.ui.data.CmcCoinsRepo;
import com.carbit3333333.loftcoin2.ui.data.Coin;
import com.carbit3333333.loftcoin2.ui.data.CoinsRepo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RatesViewModel extends ViewModel {
    private final MutableLiveData<List<Coin>> coins = new MutableLiveData<>();
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private Future<?> future;
    private final CoinsRepo repo;
   public LiveData<List<Coin>> coins(){
        return coins;
    }

    public RatesViewModel() {
        repo = new CmcCoinsRepo();
        refresh();
    }
    final void refresh() {

        future= executor.submit(() -> {
            try {
                coins.postValue(new ArrayList<>(repo.listings("USD")));
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
