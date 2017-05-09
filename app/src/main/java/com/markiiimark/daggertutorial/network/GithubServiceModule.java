package com.markiiimark.daggertutorial.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.markiiimark.daggertutorial.GithubApplicationScope;

import org.joda.time.DateTime;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MarkiiimarK on 5/9/17.
 */

@Module(includes = {NetworkModule.class})
public class GithubServiceModule {

    @Provides @GithubApplicationScope
    public GithubService provideGithubService(Retrofit githubRetrofit) {
        return githubRetrofit.create(GithubService.class);
    }

    @Provides @GithubApplicationScope
    public Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeConverter());
        return gsonBuilder.create();
    }

    @Provides @GithubApplicationScope
    public Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl("https://api.github.com/")
                .build();
    }
}
