package com.usc.searchonfb.model;

import com.usc.searchonfb.FacebookApplication;

/**
 * Created by adarsh on 4/19/2017.
 */

public class FacebookApplicationModel {

    private static FacebookApplicationModel facebookApplicationModel = new FacebookApplicationModel();

    double lat =0;

    double lon =0;

    private FacebookApplicationModel(){

    }

    public static FacebookApplicationModel getFacebookApplicationModel(){
        return facebookApplicationModel;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
