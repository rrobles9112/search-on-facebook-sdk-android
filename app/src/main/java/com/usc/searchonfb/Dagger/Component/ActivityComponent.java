package com.usc.searchonfb.dagger.component;

import android.app.Activity;

import com.usc.searchonfb.dagger.module.ActivityModule;
import com.usc.searchonfb.dagger.scope.PerActivity;

import dagger.Component;

/**
 * Created by adarsh on 4/16/2017.
 */

@PerActivity
@Component(dependencies = NetComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    //Exposed to sub-graphs.
    Activity activity();
}
