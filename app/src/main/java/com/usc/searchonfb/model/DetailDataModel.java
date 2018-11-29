package com.usc.searchonfb.model;

import android.util.Log;

import com.usc.searchonfb.presenter.contract.DetailsPresenterContract;
import com.usc.searchonfb.presenter.contract.MainPresenterContract;
import com.usc.searchonfb.rest.model.DetailModel.DetailsData;
import com.usc.searchonfb.rest.model.SearchModel.SearchDataList;
import com.usc.searchonfb.rest.networkInterface.GetDetailService;
import com.usc.searchonfb.rest.networkInterface.GetSearchService;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by adarsh on 4/4/2017.
 */

public class DetailDataModel implements DetailsPresenterContract.Model {

    Retrofit mRetrofit;

    DetailsPresenterContract.ModelCallBack mResponseCallBack;

    @Inject
    public DetailDataModel(Retrofit mRetrofit){
        this.mRetrofit = mRetrofit;
    }


    @Override
    public void onAttach(DetailsPresenterContract.ModelCallBack mResponseCallBack) {
        this.mResponseCallBack = mResponseCallBack;
    }

    @Override
    public void onDetach() {
        mResponseCallBack = null;
    }
//134972803193847
    public void loadUserDetails(String id){
        Log.i(DetailDataModel.class.getSimpleName(),"Reached here");
        Observable<DetailsData> observable = mRetrofit.create(GetDetailService.class).getDetailList(id,"correct");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<DetailsData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DetailsData mDetailsData) {
                        mResponseCallBack.onResultLoad(mDetailsData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mResponseCallBack.onErrorLoad(e.getMessage());
                        Log.i(DetailDataModel.class.getSimpleName(),"Error in loadUserDetails");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
