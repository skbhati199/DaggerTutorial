package com.markiiimark.daggertutorial.screens;

import android.app.Activity;
import android.content.Context;

import com.markiiimark.daggertutorial.GithubApplicationScope;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MarkiiimarK on 5/10/17.
 */
@Module
public class ActivityModule {

    private final Activity context;
    public ActivityModule(Activity context) {  this.context = context;  }

    @Provides @GithubApplicationScope
    @Named("ACTIVITY_CONTEXT")
    public Context context() {  return context;  }
}
