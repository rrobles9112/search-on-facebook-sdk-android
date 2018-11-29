package com.usc.searchonfb.dagger.module;

import com.usc.searchonfb.dagger.scope.PerActivity;
import com.usc.searchonfb.model.DetailDataModel;
import com.usc.searchonfb.presenter.DetailActivityPresenter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by adarsh on 4/16/2017.
 */

@Module
public class DetailsFragmentModule {

    public DetailsFragmentModule(){

    }
    @Provides
    @PerActivity
    DetailActivityPresenter provideMainPresenter(DetailDataModel mModel) {
        return new DetailActivityPresenter(mModel);
    }
    @Provides
    @PerActivity
    DetailDataModel provideDataFetchModel(Retrofit mRetrofit){
        return new DetailDataModel(mRetrofit);
    }

}
