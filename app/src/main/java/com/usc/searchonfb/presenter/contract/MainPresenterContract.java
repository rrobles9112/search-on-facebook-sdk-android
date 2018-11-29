package com.usc.searchonfb.presenter.contract;

import com.usc.searchonfb.presenter.basepresenter.BasePresenterInterface;
import com.usc.searchonfb.rest.model.SearchModel.Paging;
import com.usc.searchonfb.rest.model.SearchModel.SearchData;

import java.util.List;

/**
 * Created by adarsh on 4/4/2017.
 */

public class MainPresenterContract {

    public interface View {
        void addResults(List<SearchData> searchDataList,Paging mPaging);
        void clearResults();
        void showContentLoading();
        void hideContentLoading();
        void showListLoading();
        void hideListLoading();
        void showContentError();
        void hideContentError();
        void showListError();
        void showEmptyResultsView(Paging mPaging);
        void hideEmptyResultsView();
    }
    public interface Presenter extends BasePresenterInterface<View> {
        void load(String query, int offset, String url);
        void load(String query, int offset, String url, double lat, double lon);
        void loadMore();
        void queryChanged(String query);
        void listItemClicked(SearchData searchData);
    }
    public interface Model{
       void onAttach(MainPresenterContract.ModelCallBack mResponseCallBack);
       void onDetach();
    }
    public interface ModelCallBack{
        void onResultLoad(List<SearchData> searchDataList, Paging paging);
        void onResultLoadMore(List<SearchData> searchDataList, Paging paging);
        void onErrorLoad(String mErrorMessage);
        void onErrorLoadMore(String mErrorMessage);
    }

}
