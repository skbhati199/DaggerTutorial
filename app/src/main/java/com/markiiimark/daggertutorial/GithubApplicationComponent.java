package com.markiiimark.daggertutorial;

import com.markiiimark.daggertutorial.network.GithubService;
import com.markiiimark.daggertutorial.network.GithubServiceModule;
import com.markiiimark.daggertutorial.network.PicassoModule;
import com.squareup.picasso.Picasso;

import dagger.Component;

/**
 * Created by MarkiiimarK on 5/9/17.
 */

@Component(modules = {GithubServiceModule.class, PicassoModule.class})
public interface GithubApplicationComponent {
    // GithubServiceModule contains NetworkModule
    // NetworkModule contains ContextModule
    // SO we won't have to list all modules in the annotation.

    Picasso getPicasso();
    GithubService getGithubService();
}
