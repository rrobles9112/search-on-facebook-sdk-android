package com.usc.searchonfb.utils;

import android.net.Uri;
import android.util.Log;

import com.usc.searchonfb.rest.model.SearchModel.Paging;

public class NextPrevUtil {

    public static String getNextOffset(Paging mPaging) {
        String value = null;
        if (mPaging != null) {
            if (mPaging.getNext() != null) {
                if (mPaging.getNext().trim().length() != 0) {
                    value = EncodeURIComponent.encodeURI(mPaging.getNext());
                }
            }
        }
        return value;
    }

    public static String getPreviosOffset(Paging mPaging) {
        String value = null;
        if (mPaging != null) {
            if (mPaging.getPrevious() != null) {
                if (mPaging.getPrevious().trim().length() != 0) {
                    value = EncodeURIComponent.encodeURI(mPaging.getPrevious());
                }
            }
        }
        return value;
    }
}
