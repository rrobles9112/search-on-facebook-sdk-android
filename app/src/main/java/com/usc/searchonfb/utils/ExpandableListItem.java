package com.usc.searchonfb.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by adarsh on 4/17/2017.
 */

public class ExpandableListItem{
    List<String> mHeaderList = null;
    HashMap<String, List<String>> mExpandableListData = new HashMap<String, List<String>>();

    public ExpandableListItem(List<String> mHeaderList, HashMap<String, List<String>> mExpandableListData) {
        this.mHeaderList = mHeaderList;
        this.mExpandableListData = mExpandableListData;
    }

    public List<String> getmHeaderList() {
        return mHeaderList;
    }

    public void setmHeaderList(ArrayList<String> mHeaderList) {
        this.mHeaderList = mHeaderList;
    }

    public HashMap<String, List<String>> getmExpandableListData() {
        return mExpandableListData;
    }

    public void setmExpandableListData(HashMap<String, List<String>> mExpandableListData) {
        this.mExpandableListData = mExpandableListData;
    }
}
