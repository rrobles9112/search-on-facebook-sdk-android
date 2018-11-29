package com.usc.searchonfb.rest.model.DetailModel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by adarsh on 4/15/2017.
 */

public class DataAlbums implements Parcelable {

    String name;

    Photos photos;

    String id;

    public DataAlbums(String name, Photos photos, String id) {
        this.name = name;
        this.photos = photos;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Photos getPhotos() {
        return photos;
    }

    public String getId() {
        return id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeParcelable(this.photos, flags);
        dest.writeString(this.id);
    }

    protected DataAlbums(Parcel in) {
        this.name = in.readString();
        this.photos = in.readParcelable(Photos.class.getClassLoader());
        this.id = in.readString();
    }

    public static final Parcelable.Creator<DataAlbums> CREATOR = new Parcelable.Creator<DataAlbums>() {
        @Override
        public DataAlbums createFromParcel(Parcel source) {
            return new DataAlbums(source);
        }

        @Override
        public DataAlbums[] newArray(int size) {
            return new DataAlbums[size];
        }
    };
}
