package com.usc.searchonfb.rest.model.SearchModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by adarsh on 4/15/2017.
 */

public class SearchData implements Parcelable {

    String id;

    String link;

    String name;

    @SerializedName("picture")
    Picture picture;

    public SearchData(String id, String link, String name, Picture picture) {
        this.id = id;
        this.link = link;
        this.name = name;
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }

    public Picture getPicture() {
        return picture;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.link);
        dest.writeString(this.name);
        dest.writeParcelable(this.picture, flags);
    }

    protected SearchData(Parcel in) {
        this.id = in.readString();
        this.link = in.readString();
        this.name = in.readString();
        this.picture = in.readParcelable(Picture.class.getClassLoader());
    }

    public static final Parcelable.Creator<SearchData> CREATOR = new Parcelable.Creator<SearchData>() {
        @Override
        public SearchData createFromParcel(Parcel source) {
            return new SearchData(source);
        }

        @Override
        public SearchData[] newArray(int size) {
            return new SearchData[size];
        }
    };
}
