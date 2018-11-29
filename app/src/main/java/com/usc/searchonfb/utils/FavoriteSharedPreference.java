package com.usc.searchonfb.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.usc.searchonfb.FacebookApplication;
import com.usc.searchonfb.rest.model.SearchModel.Data;
import com.usc.searchonfb.rest.model.SearchModel.Picture;
import com.usc.searchonfb.rest.model.SearchModel.SearchData;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.usc.searchonfb.utils.Constants.CONST_EVENT;
import static com.usc.searchonfb.utils.Constants.CONST_GROUP;
import static com.usc.searchonfb.utils.Constants.CONST_ID;
import static com.usc.searchonfb.utils.Constants.CONST_LINK;
import static com.usc.searchonfb.utils.Constants.CONST_NAME;
import static com.usc.searchonfb.utils.Constants.CONST_PAGE;
import static com.usc.searchonfb.utils.Constants.CONST_PICTURE_URL;
import static com.usc.searchonfb.utils.Constants.CONST_PLACE;
import static com.usc.searchonfb.utils.Constants.CONST_TYPE;
import static com.usc.searchonfb.utils.Constants.CONST_USER;

/**
 * Created by adarsh on 4/16/2017.
 */

public class FavoriteSharedPreference {

    private static final int PREFERENCE_MODE_PRIVATE = 0;

    public static boolean addFavoriteItem(Context app, SearchData mData, String type) {
        SharedPreferences mSharedPreferneces = getSharedPreference(app, type);
        SharedPreferences.Editor mEditor = mSharedPreferneces.edit();
        JSONObject favoriteItem = new JSONObject();

        String name = "";
        String id = "";
        String pictureUrl = "";
        String link = "";

        if (mData != null) {
            if (mData.getId() != null) {
                id = mData.getId();
            }

            if (mData.getName() != null) {
                name = mData.getName();
            }

            if (mData.getLink() != null) {
                link = mData.getLink();
            }

            if (mData.getPicture() != null && mData.getPicture().getData() != null
                    && mData.getPicture().getData().getUrl() != null) {
                pictureUrl = mData.getPicture().getData().getUrl();
            }
        }

        try {
            favoriteItem.put(CONST_NAME, name);
            favoriteItem.put(CONST_ID, id);
            favoriteItem.put(CONST_PICTURE_URL, pictureUrl);
            favoriteItem.put(CONST_TYPE, type);
            favoriteItem.put(CONST_LINK, link);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (mData.getId() != null) {
            mEditor.putString(mData.getId(), favoriteItem.toString()).apply();
            return true;
        }

        return false;
    }

    public static boolean isFavorite(Context app, SearchData mData, String type) {
        SharedPreferences mSharedPreferneces = getSharedPreference(app, type);
        Map<String, ?> allEntries = mSharedPreferneces.getAll();
        if (allEntries.containsKey(mData.getId())) {
            return true;
        }
        return false;
    }

    public static boolean deleteFavoriteItem(Context app, SearchData mData, String type) {
        SharedPreferences mSharedPreferneces = getSharedPreference(app, type);
        mSharedPreferneces.edit().remove(mData.getId()).apply();
        return true;
    }

    public static List<SearchData> getFavouriteList(Context app, String type) {
        List<SearchData> mSearchDataList = new ArrayList<SearchData>();
        SharedPreferences mSharedPrefereneces = getSharedPreference(app, type);
        Map<String, ?> keys = mSharedPrefereneces.getAll();

        for (Map.Entry<String, ?> entry : keys.entrySet()) {
            Log.i("map values", entry.getKey() + ": " + entry.getValue().toString());
            String strJson = mSharedPrefereneces.getString(entry.getKey(), "0");
            if (!strJson.equals("0")) {
                try {
                    JSONObject jsonData = new JSONObject(strJson);
                    SearchData mSearchData = getSearchData(jsonData);
                    mSearchDataList.add(mSearchData);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return mSearchDataList;
    }

    private static SearchData getSearchData(JSONObject jsonData) {
        String id = "";
        String name = "";
        String pictureUrl = "";
        String type = "";
        String link = "";

        try {
            id = jsonData.getString(CONST_ID);
            name = jsonData.getString(CONST_NAME);
            pictureUrl = jsonData.getString(CONST_PICTURE_URL);
            type = jsonData.getString(CONST_TYPE);
            link = jsonData.getString(CONST_LINK);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Picture picture = new Picture(new Data(pictureUrl));
        SearchData mData = new SearchData(id, link, name, picture);
        return mData;
    }

    private static synchronized SharedPreferences getSharedPreference(Context app, String type) {
        SharedPreferences mSharedPreferneces;
       //get the shared preferences, type is users, places, groups, pages..
        mSharedPreferneces = app
                .getSharedPreferences(type, PREFERENCE_MODE_PRIVATE);

        return mSharedPreferneces;
    }

}
