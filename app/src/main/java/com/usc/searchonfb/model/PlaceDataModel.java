package com.usc.searchonfb.model;

import android.util.Log;

import com.usc.searchonfb.presenter.contract.MainPresenterContract;
import com.usc.searchonfb.rest.model.SearchModel.SearchDataList;
import com.usc.searchonfb.rest.networkInterface.GetLocationSearchService;
import com.usc.searchonfb.rest.networkInterface.GetSearchService;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class PlaceDataModel implements MainPresenterContract.Model {

    Retrofit mRetrofit;

    MainPresenterContract.ModelCallBack mResponseCallBack;

    @Inject
    public PlaceDataModel(Retrofit mRetrofit){
        this.mRetrofit = mRetrofit;
    }

    @Override
    public void onAttach(MainPresenterContract.ModelCallBack mResponseCallBack) {
        this.mResponseCallBack = mResponseCallBack;
    }

    @Override
    public void onDetach() {
        mResponseCallBack = null;
    }

    public void loadUserDetails(String SearchQuery, int offset, String url){
        //Log.i(PlaceDataModel.class.getSimpleName(),"Reached here");
        Observable<SearchDataList> observable;
        if(url==null){
            double lat = FacebookApplicationModel.getFacebookApplicationModel().getLat();
            double lng = FacebookApplicationModel.getFacebookApplicationModel().getLon();
            if(lat!=0 && lng!=0){
                observable = mRetrofit.create(GetLocationSearchService.class).getDataList(SearchQuery,"Place",lat,lng,offset);
            }else{
                observable = mRetrofit.create(GetSearchService.class).getDataList(SearchQuery,"Place",offset);
            }

        }else{
            observable = mRetrofit.create(GetSearchService.class).getDataListUrl(url);
        }
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<SearchDataList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchDataList mSearchDataList) {
                        mResponseCallBack.onResultLoad(mSearchDataList.getSearchDataList(),mSearchDataList.getPaging());
                    }

                    @Override
                    public void onError(Throwable e) {
                        mResponseCallBack.onErrorLoad(e.getMessage());
                        Log.i(PlaceDataModel.class.getSimpleName(),"Error in loadUserDetails");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
