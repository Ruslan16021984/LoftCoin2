package com.carbit3333333.loftcoin2.data;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.ResponseBody;
import retrofit2.Response;

@Singleton
public
class CmcCoinsRepo implements CoinsRepo {

    private final CmcApi api;

    @Inject
    public CmcCoinsRepo(CmcApi cmcApi) {
        this.api = cmcApi;
    }

    @NonNull
    @Override
    public List<? extends Coin> listings(@NonNull String currency) throws IOException {
        final Response<Listings> response = api.listings(currency).execute();
        if (response.isSuccessful()) {
            final Listings listings = response.body();
            if (listings != null) {
                return listings.data();
            }
        } else {
            final ResponseBody responseBody = response.errorBody();
            if (responseBody != null) {
                throw new IOException(responseBody.string());
            }
        }
        return Collections.emptyList();
    }

}
