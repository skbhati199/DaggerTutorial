package com.infoskillstechnology.daggar2tutorial.screens;

import com.infoskillstechnology.daggar2tutorial.screens.home.AdapterRepos;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Skbhati on 25-09-2017.
 */

@Module
public class HomeActivityModule {

    public final HomeActivity homeActivity;

    public HomeActivityModule(HomeActivity homeActivity) {
        this.homeActivity = homeActivity;
    }

    @Provides
    @HomeActivityScope
    public AdapterRepos adapterRepos(Picasso picasso){
        return new AdapterRepos(homeActivity,picasso);
    }
}
