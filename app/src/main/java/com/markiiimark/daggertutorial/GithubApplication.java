package com.markiiimark.daggertutorial;

import android.app.Activity;
import android.app.Application;

import com.markiiimark.daggertutorial.network.GithubService;
import com.markiiimark.daggertutorial.network.GithubServiceModule;
import com.markiiimark.daggertutorial.network.NetworkModule;
import com.markiiimark.daggertutorial.network.PicassoModule;
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

        Timber.plant(new Timber.DebugTree());

        GithubApplicationComponent component = DaggerGithubApplicationComponent.builder()
                .contextModule(new ContextModule(this))
//                .githubServiceModule(new GithubServiceModule())
//                .networkModule(new NetworkModule())
//                .picassoModule(new PicassoModule())
                .build();

        githubService = component.getGithubService();
        picasso = component.getPicasso();
    }

    public GithubService getGithubService() {  return githubService;  }
    public Picasso getPicasso() {  return picasso;  }
}
