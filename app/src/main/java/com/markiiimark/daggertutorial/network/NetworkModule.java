package com.markiiimark.daggertutorial.network;

import android.content.Context;

import com.markiiimark.daggertutorial.ApplicationContext;
import com.markiiimark.daggertutorial.ContextModule;
import com.markiiimark.daggertutorial.GithubApplicationScope;

import java.io.File;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

/**
 * Created by MarkiiimarK on 5/9/17.
 */

@Module(includes = {ContextModule.class})
public class NetworkModule {

    @Provides @GithubApplicationScope
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {  Timber.i(message);  }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return interceptor;
    }

    @Provides @GithubApplicationScope
    public Cache provideCache(File cacheFile) {
        return new Cache(cacheFile, 10 * 1000 * 1000); // 10MB Cache
    }


    @Provides @GithubApplicationScope
    public File provideCacheFile(@ApplicationContext Context context) {
        return new File(context.getCacheDir(), "okHttp_cache");
    }

    @Provides @GithubApplicationScope
    public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor, Cache cache) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .cache(cache)
                .build();
    }
}
