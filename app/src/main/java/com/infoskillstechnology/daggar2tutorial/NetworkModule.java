package com.infoskillstechnology.daggar2tutorial;

import android.content.Context;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

/**
 * Created by Skbhati on 25-09-2017.
 */

@Module(includes = ContextModule.class)
public class NetworkModule {


    @Provides
    HttpLoggingInterceptor httpLoggingInterceptor(){
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.i(message);
            }
        });
    }

    @Provides
    public Cache cache(File cacheFile){
        return new Cache(cacheFile, 10 * 1024 * 1024); //10 MB
    }

    @Provides
    public File file(Context context){
        return new File(context.getCacheDir(), "okhttp_cache");
    }

    @Provides
    public OkHttpClient okHttpClient(HttpLoggingInterceptor httpLoggingInterceptor, Cache cache) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .cache(cache)
                .build();
    }



}
