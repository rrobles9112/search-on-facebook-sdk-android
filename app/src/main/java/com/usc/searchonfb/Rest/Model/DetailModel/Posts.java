package com.usc.searchonfb.rest.model.DetailModel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adarsh on 4/15/2017.
 */

public class Posts implements Parcelable {

    List<PostsData> data;

    public Posts(List<PostsData> data) {
        this.data = data;
    }

    public List<PostsData> getData() {
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

    protected Posts(Parcel in) {
        this.data = new ArrayList<PostsData>();
        in.readList(this.data, PostsData.class.getClassLoader());
    }

    public static final Parcelable.Creator<Posts> CREATOR = new Parcelable.Creator<Posts>() {
        @Override
        public Posts createFromParcel(Parcel source) {
            return new Posts(source);
        }

        @Override
        public Posts[] newArray(int size) {
            return new Posts[size];
        }
    };
}
