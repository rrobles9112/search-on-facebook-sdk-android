package com.usc.searchonfb.presenter.contract;

import com.usc.searchonfb.presenter.basepresenter.BasePresenterInterface;
import com.usc.searchonfb.rest.model.DetailModel.DetailsData;
import com.usc.searchonfb.rest.model.SearchModel.SearchData;

import java.util.List;

/**
 * Created by adarsh on 4/4/2017.
 */

public class DetailsPresenterContract {

    public interface View {
        void addResults(DetailsData mDetailsData);
        void clearResults();
        void showContentLoading();
        void hideContentLoading();
        void showListLoading();
        void hideListLoading();
        void showContentError();
        void hideContentError();
        void showListError();
        void showEmptyResultsView();
        void hideEmptyResultsView();
    }
    public interface Presenter extends BasePresenterInterface<View> {
        void load(String id);
        void loadMore();
        void queryChanged(String id);
       // void listItemClicked(SearchData searchData);
    }
    public interface Model{
       void onAttach(DetailsPresenterContract.ModelCallBack mResponseCallBack);
       void onDetach();
    }
    public interface ModelCallBack{
        void onResultLoad(DetailsData mDetailsData);
        void onResultLoadMore(DetailsData mDetailsData);
        void onErrorLoad(String mErrorMessage);
        void onErrorLoadMore(String mErrorMessage);
    }

}
