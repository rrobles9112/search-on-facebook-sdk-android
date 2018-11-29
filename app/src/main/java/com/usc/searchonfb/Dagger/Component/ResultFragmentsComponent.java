package com.usc.searchonfb.dagger.component;

import com.usc.searchonfb.dagger.module.ActivityModule;
import com.usc.searchonfb.dagger.module.ResultFragmentModule;
import com.usc.searchonfb.dagger.scope.PerActivity;
import com.usc.searchonfb.view.fragment.EventFragment;
import com.usc.searchonfb.view.fragment.GroupFragment;
import com.usc.searchonfb.view.fragment.PageFragment;
import com.usc.searchonfb.view.fragment.PlaceFragment;
import com.usc.searchonfb.view.fragment.UserFragment;

import dagger.Component;

/**
 * Created by adarsh on 4/16/2017.
 */

@PerActivity
@Component(dependencies = NetComponent.class,
        modules = {ActivityModule.class, ResultFragmentModule.class})
public interface ResultFragmentsComponent extends ActivityComponent {
    void inject(UserFragment userFragment);
    void inject(PlaceFragment placeFragment);
    void inject(EventFragment eventFragment);
    void inject(PageFragment pageFragment);
    void inject(GroupFragment groupFragment);
}

