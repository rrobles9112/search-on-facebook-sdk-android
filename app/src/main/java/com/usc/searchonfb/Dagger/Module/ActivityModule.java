package com.usc.searchonfb.dagger.module;

import android.app.Activity;

import com.usc.searchonfb.dagger.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by adarsh on 4/16/2017.
 */

@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Activity activity() {
        return this.activity;
    }
}
