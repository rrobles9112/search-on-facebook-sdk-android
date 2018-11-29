package com.usc.searchonfb.model;

import android.util.Log;

import com.usc.searchonfb.presenter.contract.MainPresenterContract;
import com.usc.searchonfb.rest.model.SearchModel.SearchDataList;
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

public class UserDataModel implements MainPresenterContract.Model {

    Retrofit mRetrofit;

    MainPresenterContract.ModelCallBack mResponseCallBack;

    @Inject
    public UserDataModel(Retrofit mRetrofit){
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
        //Log.i(UserDataModel.class.getSimpleName(),"Reached here");

        Observable<SearchDataList> observable;
        if(url==null){
            observable = mRetrofit.create(GetSearchService.class).getDataList(SearchQuery,"User",offset);
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
                        Log.i(UserDataModel.class.getSimpleName(),"Error in loadUserDetails");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
