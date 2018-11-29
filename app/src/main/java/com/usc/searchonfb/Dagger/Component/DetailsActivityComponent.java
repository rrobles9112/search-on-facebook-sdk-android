package com.usc.searchonfb.dagger.component;

import com.usc.searchonfb.dagger.module.DetailsFragmentModule;
import com.usc.searchonfb.dagger.scope.PerActivity;
import com.usc.searchonfb.view.activity.DetailsActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = NetComponent.class, modules = DetailsFragmentModule.class)
public interface DetailsActivityComponent {
    void inject(DetailsActivity activity);
}