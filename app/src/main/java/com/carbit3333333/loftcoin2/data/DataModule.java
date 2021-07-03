package com.carbit3333333.loftcoin2.data;

import com.carbit3333333.loftcoin2.BuildConfig;
import com.squareup.moshi.Moshi;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public abstract class DataModule {
    @Provides
    @Singleton
    static OkHttpClient okHttpClient(){
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(chain -> {
            final Request request = chain.request();
            return chain.proceed(request.newBuilder()
                    .addHeader(CmcApi.API_KEY, BuildConfig.API_KEY)
                    .build());
        });
        if (BuildConfig.DEBUG) {
            final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            interceptor.redactHeader(CmcApi.API_KEY);
            builder.addInterceptor(interceptor);
        }
        return builder.build();
    }
    @Provides
    static Moshi moshi(){
        final Moshi moshi = new Moshi.Builder().build();
        return moshi.newBuilder().add(Coin.class, moshi.adapter(AutoValue_CmcCoin.class))
                .add(Listings.class, moshi.adapter(AutoValue_Listings.class))
                .build();
    }
    @Provides
    static Retrofit retrofit(OkHttpClient okHttpClient, Moshi moshi){
        final Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(okHttpClient);
        builder.baseUrl(BuildConfig.API_ENDPOINT);
        builder.addConverterFactory(MoshiConverterFactory.create(moshi));
        return builder.build();
    }
    @Provides
    static CmcApi cmcApi(Retrofit retrofit){
        return retrofit.create(CmcApi.class);
    }
    @Binds
    abstract CoinsRepo coinsRepo(CmcCoinsRepo cmcCoinsRepo);
    @Binds
    abstract CurrencyRepo currencyRepo(CurrencyRepoImpl currencyRepo);
}
