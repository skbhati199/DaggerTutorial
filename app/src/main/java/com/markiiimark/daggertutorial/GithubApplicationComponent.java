package com.markiiimark.daggertutorial;

import com.markiiimark.daggertutorial.network.GithubService;
import com.markiiimark.daggertutorial.network.GithubServiceModule;
import com.markiiimark.daggertutorial.network.PicassoModule;
import com.squareup.picasso.Picasso;

import dagger.Component;

/**
 * Created by MarkiiimarK on 5/9/17.
 */

@GithubApplicationScope
@Component(modules = {GithubServiceModule.class, PicassoModule.class})
public interface GithubApplicationComponent {
    Picasso getPicasso();
    GithubService getGithubService();
}
