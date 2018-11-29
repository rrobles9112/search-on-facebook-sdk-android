package com.usc.searchonfb.rest.model.SearchModel;

import android.os.Parcel;
import android.os.Parcelable;

public class Data implements Parcelable {

    String url;


    public Data(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
    }

    protected Data(Parcel in) {
        this.url = in.readString();
    }

    public static final Parcelable.Creator<Data> CREATOR = new Parcelable.Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel source) {
            return new Data(source);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };
}
