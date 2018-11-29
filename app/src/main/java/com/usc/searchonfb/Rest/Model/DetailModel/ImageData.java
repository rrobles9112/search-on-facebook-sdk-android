package com.usc.searchonfb.rest.model.DetailModel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adarsh on 4/15/2017.
 */

public class ImageData implements Parcelable {

    List<Images> images;

    public ImageData(List<Images> images) {
        this.images = images;
    }

    public List<Images> getImages() {
        return images;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.images);
    }

    protected ImageData(Parcel in) {
        this.images = new ArrayList<Images>();
        in.readList(this.images, Images.class.getClassLoader());
    }

    public static final Parcelable.Creator<ImageData> CREATOR = new Parcelable.Creator<ImageData>() {
        @Override
        public ImageData createFromParcel(Parcel source) {
            return new ImageData(source);
        }

        @Override
        public ImageData[] newArray(int size) {
            return new ImageData[size];
        }
    };
}
