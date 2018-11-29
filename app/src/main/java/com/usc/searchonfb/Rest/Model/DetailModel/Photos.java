package com.usc.searchonfb.rest.model.DetailModel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adarsh on 4/15/2017.
 */

public class Photos implements Parcelable {
    List<ImageData> data;

    public Photos(List<ImageData> data) {
        this.data = data;
    }

    public List<ImageData> getData() {
        return data;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.data);
    }

    protected Photos(Parcel in) {
        this.data = new ArrayList<ImageData>();
        in.readList(this.data, ImageData.class.getClassLoader());
    }

    public static final Parcelable.Creator<Photos> CREATOR = new Parcelable.Creator<Photos>() {
        @Override
        public Photos createFromParcel(Parcel source) {
            return new Photos(source);
        }

        @Override
        public Photos[] newArray(int size) {
            return new Photos[size];
        }
    };
}
