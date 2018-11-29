package com.usc.searchonfb.presenter;

import android.util.Log;

import com.usc.searchonfb.model.DetailDataModel;
import com.usc.searchonfb.presenter.contract.DetailsPresenterContract;
import com.usc.searchonfb.rest.model.DetailModel.DetailsData;

import javax.inject.Inject;

/**
 * Created by adarsh on 4/4/2017.
 */

public class DetailActivityPresenter implements DetailsPresenterContract.Presenter, DetailsPresenterContract.ModelCallBack {

    DetailsPresenterContract.View mMainView = null;

    DetailDataModel model;


    @Inject
    public DetailActivityPresenter(DetailDataModel pDataModel) {
        model = pDataModel;
        model.onAttach(this);
    }

    @Override
    public void attach(DetailsPresenterContract.View view) {
        mMainView = view;
    }

    @Override
    public void detach() {
        mMainView = null;
    }


    @Override
    public void load(String searchQuery) {
        if (mMainView != null) {
            model.loadUserDetails(searchQuery);
        } else {
            Log.i("Adarsh", "mMainView is null");
        }
    }

    @Override
    public void loadMore() {
        if (mMainView != null) {

        }
    }

    @Override
    public void queryChanged(String query) {
        if (mMainView != null) {

        }
    }

    //These are the main model call backs

    @Override
    public void onResultLoadMore(DetailsData mData) {
        if (mMainView != null) {
            mMainView.addResults(mData);
        }
    }

    @Override
    public void onResultLoad(DetailsData mData) {
        if (mMainView != null) {
            //Log.i("Adarsh","I am here1 dude");
            if (mData == null) {
                mMainView.showEmptyResultsView();
            } else {
                mMainView.clearResults();
                mMainView.addResults(mData);
            }
        } else {
            //Log.i("Adarsh","I am here dude");
        }
    }

    @Override
    public void onErrorLoad(String mErrorMessage) {
        if (mMainView != null) {
            mMainView.showContentError();
        }
    }

    @Override
    public void onErrorLoadMore(String mErrorMessage) {
        if (mMainView != null) {
            mMainView.showContentError();
        }
    }
}
