package com.usc.searchonfb.rest.model.DetailModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.usc.searchonfb.rest.model.SearchModel.Picture;

/**
 * Created by adarsh on 4/15/2017.
 */

public class DetailsData implements Parcelable {

    String id;

    String name;

    Picture picture;

    Albums albums;

    Posts posts;

    public DetailsData(String id, String name, Picture picture, Albums albums, Posts posts) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.albums = albums;
        this.posts = posts;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Picture getPicture() {
        return picture;
    }

    public Albums getAlbums() {
        return albums;
    }

    public Posts getPosts() {
        return posts;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeParcelable(this.picture, flags);
        dest.writeParcelable(this.albums, flags);
        dest.writeParcelable(this.posts, flags);
    }

    protected DetailsData(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.picture = in.readParcelable(Picture.class.getClassLoader());
        this.albums = in.readParcelable(Albums.class.getClassLoader());
        this.posts = in.readParcelable(Posts.class.getClassLoader());
    }

    public static final Parcelable.Creator<DetailsData> CREATOR = new Parcelable.Creator<DetailsData>() {
        @Override
        public DetailsData createFromParcel(Parcel source) {
            return new DetailsData(source);
        }

        @Override
        public DetailsData[] newArray(int size) {
            return new DetailsData[size];
        }
    };
}
