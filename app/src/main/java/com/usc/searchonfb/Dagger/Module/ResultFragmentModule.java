package com.usc.searchonfb.dagger.module;

import com.usc.searchonfb.dagger.scope.PerActivity;
import com.usc.searchonfb.model.EventDataModel;
import com.usc.searchonfb.model.GroupDataModel;
import com.usc.searchonfb.model.PageDataModel;
import com.usc.searchonfb.model.PlaceDataModel;
import com.usc.searchonfb.model.UserDataModel;
import com.usc.searchonfb.presenter.EventFragmentPresenter;
import com.usc.searchonfb.presenter.GroupFragmentPresenter;
import com.usc.searchonfb.presenter.PageFragmentPresenter;
import com.usc.searchonfb.presenter.PlaceFragmentPresenter;
import com.usc.searchonfb.presenter.UserFragmentPresenter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by adarsh on 4/16/2017.
 */
@Module
public class ResultFragmentModule {

    public ResultFragmentModule() {

    }

    @Provides
    @PerActivity
    UserFragmentPresenter provideUserPresenter(UserDataModel mModel) {
        return new UserFragmentPresenter(mModel);
    }

    @Provides
    @PerActivity
    UserDataModel provideUserDataFetchModel(Retrofit mRetrofit) {
        return new UserDataModel(mRetrofit);
    }

    @Provides
    @PerActivity
    PageFragmentPresenter providePagePresenter(PageDataModel mModel) {
        return new PageFragmentPresenter(mModel);
    }

    @Provides
    @PerActivity
    PageDataModel providePageDataFetchModel(Retrofit mRetrofit) {
        return new PageDataModel(mRetrofit);
    }

    @Provides
    @PerActivity
    EventFragmentPresenter provideEventPresenter(EventDataModel mModel) {
        return new EventFragmentPresenter(mModel);
    }

    @Provides
    @PerActivity
    EventDataModel provideEventDataFetchModel(Retrofit mRetrofit) {
        return new EventDataModel(mRetrofit);
    }

    @Provides
    @PerActivity
    PlaceFragmentPresenter providePlacePresenter(PlaceDataModel mModel) {
        return new PlaceFragmentPresenter(mModel);
    }

    @Provides
    @PerActivity
    PlaceDataModel providePlaceDataFetchModel(Retrofit mRetrofit) {
        return new PlaceDataModel(mRetrofit);
    }

    @Provides
    @PerActivity
    GroupFragmentPresenter provideGroupPresenter(GroupDataModel mModel) {
        return new GroupFragmentPresenter(mModel);
    }

    @Provides
    @PerActivity
    GroupDataModel provideGroupDataFetchModel(Retrofit mRetrofit) {
        return new GroupDataModel(mRetrofit);
    }
}

