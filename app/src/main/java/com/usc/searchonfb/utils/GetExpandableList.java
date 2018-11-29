package com.usc.searchonfb.utils;

import com.usc.searchonfb.rest.model.DetailModel.Albums;
import com.usc.searchonfb.rest.model.DetailModel.DataAlbums;
import com.usc.searchonfb.rest.model.DetailModel.DetailsData;
import com.usc.searchonfb.rest.model.DetailModel.ImageData;
import com.usc.searchonfb.rest.model.DetailModel.Images;
import com.usc.searchonfb.rest.model.DetailModel.Photos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by adarsh on 4/17/2017.
 */

public class GetExpandableList {

    private static GetExpandableList expandableList = null;

    public static GetExpandableList getExpandableInstance() {
       if(expandableList==null){
           expandableList = new GetExpandableList();
       }
       return expandableList;
    }

    private GetExpandableList() {

    }

    public ExpandableListItem getExpandableListData(DetailsData mDetailData) {
        HashMap<String, List<String>> mExpandableListData = new HashMap<String, List<String>>();
        List<String> mHeaderList = new ArrayList<String>();

        if (mDetailData.getAlbums() != null) {
            Albums mAlbums = mDetailData.getAlbums();
            if (mAlbums.getData() != null) {
                List<DataAlbums> mListAlbums = mAlbums.getData();
                for (DataAlbums eachAlbum : mListAlbums) {
                    String header = null;
                    ArrayList<String> mChildList = new ArrayList<String>();
                    if (eachAlbum.getName() != null) {
                        header = eachAlbum.getName();
                        mHeaderList.add(header);
                        Photos mPhotos = eachAlbum.getPhotos();
                        if (mPhotos!=null && mPhotos.getData() != null) {
                            List<ImageData> mListImageData = mPhotos.getData();
                            for (ImageData mImageData : mListImageData) {
                                if (mImageData.getImages() != null) {
                                    List<Images> mImages = mImageData.getImages();
                                    if(mImages.size()>0){
                                        //String image = mImages.get(0).getSource();
                                        String image = null;
                                        if(mImages.size()>2){
                                            image = mImages.get(2).getSource();
                                        }else{
                                            image = mImages.get(0).getSource();
                                        }
                                        if(image!=null){
                                            mChildList.add(image);
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if(header!=null){
                        mExpandableListData.put(header,mChildList);
                    }
                }
            }

        }
        return new ExpandableListItem(mHeaderList,mExpandableListData);
    }


}
