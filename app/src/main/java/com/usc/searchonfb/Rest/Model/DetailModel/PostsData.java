package com.usc.searchonfb.rest.model.DetailModel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by adarsh on 4/15/2017.
 */

public class PostsData implements Parcelable {

    String message;

    String created_time;

    String story;

    public PostsData(String message, String created_time, String story) {
        this.message = message;
        this.created_time = created_time;
        this.story = story;
    }

    public String getMessage() {
        return message;
    }

    public String getCreated_time() {
        return created_time;
    }

    public String getStory() {
        return story;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.message);
        dest.writeString(this.created_time);
        dest.writeString(this.story);
    }

    protected PostsData(Parcel in) {
        this.message = in.readString();
        this.created_time = in.readString();
        this.story = in.readString();
    }

    public static final Parcelable.Creator<PostsData> CREATOR = new Parcelable.Creator<PostsData>() {
        @Override
        public PostsData createFromParcel(Parcel source) {
            return new PostsData(source);
        }

        @Override
        public PostsData[] newArray(int size) {
            return new PostsData[size];
        }
    };
}
