package com.infoskillstechnology.daggar2tutorial;

import android.app.Activity;
import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Skbhati on 25-09-2017.
 */
@Module
public class ActivityModule {

    public final Activity context;

    public ActivityModule(Activity activity) {
        this.context = activity;
    }

    @Provides
    @Named("activity_context")
    public Context context(){
        return context;
    }
}
