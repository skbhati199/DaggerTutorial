package com.markiiimark.daggertutorial;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MarkiiimarK on 5/9/17.
 */

@Module
public class ContextModule {

    private final Context context;
    public ContextModule(Context context) {  this.context = context;  }

    @Provides @GithubApplicationScope
    public Context provideContext() {
        return context;
    }
}
