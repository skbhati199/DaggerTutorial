package com.infoskillstechnology.daggar2tutorial;

import com.infoskillstechnology.daggar2tutorial.network.GithubService;
import com.squareup.picasso.Picasso;

import dagger.Component;

/**
 * Created by Skbhati on 25-09-2017.
 */
@GithubApplicationScope
@Component(modules = {GithubServiceModule.class, PicassoModule.class, ActivityModule.class})
public interface GithubApplicationComponent {

    Picasso getPicasso();

    GithubService getGithubService();

}
