package com.usc.searchonfb.rest.model.SearchModel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by adarsh on 4/19/2017.
 */

public class Paging implements Parcelable {

    String next;

    String previous;

    public Paging(String next, String previous) {
        this.next = next;
        this.previous = previous;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.next);
        dest.writeString(this.previous);
    }

    protected Paging(Parcel in) {
        this.next = in.readString();
        this.previous = in.readString();
    }

    public static final Parcelable.Creator<Paging> CREATOR = new Parcelable.Creator<Paging>() {
        @Override
        public Paging createFromParcel(Parcel source) {
            return new Paging(source);
        }

        @Override
        public Paging[] newArray(int size) {
            return new Paging[size];
        }
    };
}
