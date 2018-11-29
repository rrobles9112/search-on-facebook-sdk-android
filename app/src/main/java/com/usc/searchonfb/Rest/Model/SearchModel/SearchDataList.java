package com.usc.searchonfb.rest.model.SearchModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by adarsh on 4/15/2017.
 */

public class SearchDataList implements Parcelable {

    @SerializedName("data")
    List<SearchData> searchDataList;

    Paging paging;

    public void setSearchDataList(List<SearchData> searchDataList) {
        this.searchDataList = searchDataList;
    }

    public SearchDataList(List<SearchData> searchDataList, Paging paging) {
        this.searchDataList = searchDataList;
        this.paging = paging;
    }

    public SearchDataList(List<SearchData> searchDataList) {
        this.searchDataList = searchDataList;
    }

    public List<SearchData> getSearchDataList() {
        return searchDataList;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.searchDataList);
        dest.writeParcelable(this.paging, flags);
    }

    protected SearchDataList(Parcel in) {
        this.searchDataList = in.createTypedArrayList(SearchData.CREATOR);
        this.paging = in.readParcelable(Paging.class.getClassLoader());
    }

    public static final Parcelable.Creator<SearchDataList> CREATOR = new Parcelable.Creator<SearchDataList>() {
        @Override
        public SearchDataList createFromParcel(Parcel source) {
            return new SearchDataList(source);
        }

        @Override
        public SearchDataList[] newArray(int size) {
            return new SearchDataList[size];
        }
    };
}
