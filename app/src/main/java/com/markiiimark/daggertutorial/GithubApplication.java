package com.markiiimark.daggertutorial;

import android.app.Activity;
import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.markiiimark.daggertutorial.network.DateTimeConverter;
import com.markiiimark.daggertutorial.network.GithubService;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;


public class GithubApplication extends Application {

    public static GithubApplication get(Activity activity) {
        return (GithubApplication) activity.getApplication();
    }

    private GithubService githubService;
    private Picasso picasso;

    @Override
    public void onCreate() {
        super.onCreate();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeConverter());
        Gson gson = gsonBuilder.create();

        // Instantiate Timber logger
        Timber.plant(new Timber.DebugTree());

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {  Timber.i(message);  }
        });

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        picasso = new Picasso.Builder(this)
                .downloader(new OkHttp3Downloader(okHttpClient))
                .build();

        Retrofit githubRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://api.github.com/")
                .build();

        githubService = githubRetrofit.create(GithubService.class);
    }

    public GithubService getGithubService() {  return githubService;  }
    public Picasso getPicasso() {  return picasso;  }
}
