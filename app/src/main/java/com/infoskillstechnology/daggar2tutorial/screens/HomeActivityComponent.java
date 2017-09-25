package com.infoskillstechnology.daggar2tutorial.screens;

import com.infoskillstechnology.daggar2tutorial.GithubApplicationComponent;
import com.infoskillstechnology.daggar2tutorial.network.GithubService;
import com.infoskillstechnology.daggar2tutorial.screens.home.AdapterRepos;

import dagger.Component;

/**
 * Created by Skbhati on 25-09-2017.
 */

@HomeActivityScope
@Component(modules = HomeActivityModule.class, dependencies = GithubApplicationComponent.class)
public interface HomeActivityComponent {

  void injectHomeActivity(HomeActivity activity);
}
