package com.infoskillstechnology.daggar2tutorial;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.infoskillstechnology.daggar2tutorial.network.DateTimeConverter;
import com.infoskillstechnology.daggar2tutorial.network.GithubService;

import org.joda.time.DateTime;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Skbhati on 25-09-2017.
 */

@Module(includes = NetworkModule.class)
public class GithubServiceModule {

    @Provides
    public GithubService githubService(Retrofit retrofit){
        return retrofit.create(GithubService.class);
    }

    @Provides
    public Gson gson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeConverter());
        return gsonBuilder.create();
    }

    @Provides
    public Retrofit retrofit(OkHttpClient client, Gson gson){
        return   new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .baseUrl("https://api.github.com/")
                .build();
    }
}
