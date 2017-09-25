package com.infoskillstechnology.daggar2tutorial;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Skbhati on 25-09-2017.
 */

@Module
public class ContextModule {

    private final Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context context(){
        return context;
    }
}
