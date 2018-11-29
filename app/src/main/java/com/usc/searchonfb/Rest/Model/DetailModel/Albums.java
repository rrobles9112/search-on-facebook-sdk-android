package com.usc.searchonfb.rest.model.DetailModel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adarsh on 4/15/2017.
 */

public class Albums implements Parcelable {

    List<DataAlbums> data;

    public Albums(List<DataAlbums> data) {
        this.data = data;
    }

    public List<DataAlbums> getData() {
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

    protected Albums(Parcel in) {
        this.data = new ArrayList<DataAlbums>();
        in.readList(this.data, DataAlbums.class.getClassLoader());
    }

    public static final Parcelable.Creator<Albums> CREATOR = new Parcelable.Creator<Albums>() {
        @Override
        public Albums createFromParcel(Parcel source) {
            return new Albums(source);
        }

        @Override
        public Albums[] newArray(int size) {
            return new Albums[size];
        }
    };
}
