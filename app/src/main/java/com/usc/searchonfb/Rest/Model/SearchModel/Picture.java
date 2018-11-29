package com.usc.searchonfb.rest.model.SearchModel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by adarsh on 4/15/2017.
 */

public class Picture implements Parcelable {

    Data data;

    public Picture(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.data, flags);
    }

    protected Picture(Parcel in) {
        this.data = in.readParcelable(Data.class.getClassLoader());
    }

    public static final Parcelable.Creator<Picture> CREATOR = new Parcelable.Creator<Picture>() {
        @Override
        public Picture createFromParcel(Parcel source) {
            return new Picture(source);
        }

        @Override
        public Picture[] newArray(int size) {
            return new Picture[size];
        }
    };
}
