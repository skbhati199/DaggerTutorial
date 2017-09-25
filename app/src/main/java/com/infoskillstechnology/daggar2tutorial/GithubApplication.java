package com.infoskillstechnology.daggar2tutorial;

import android.app.Activity;
import android.app.Application;

import com.infoskillstechnology.daggar2tutorial.network.GithubService;
import com.squareup.picasso.Picasso;

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
        // Instantiate Timber logger
        Timber.plant(new Timber.DebugTree());

        GithubApplicationComponent component = DaggerGithubApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .githubServiceModule(new GithubServiceModule())
                .networkModule(new NetworkModule())
                .picassoModule(new PicassoModule())
                .build();

        githubService = component.getGithubService();
        picasso = component.getPicasso();

    }

    public GithubService getGithubService() {
        return githubService;
    }

    public Picasso getPicasso() {
        return picasso;
    }
}
