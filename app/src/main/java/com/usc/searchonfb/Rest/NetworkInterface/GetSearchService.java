package com.usc.searchonfb.rest.networkInterface;

import com.usc.searchonfb.rest.model.SearchModel.SearchDataList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by adarsh on 4/15/2017.
 */

public interface GetSearchService {
    @GET("search_aws.php?")
    Observable<SearchDataList> getDataList(@Query("search_query") String query,@Query("type") String type, @Query("offset1") int offset);

    @GET("search_aws.php?")
    Observable<SearchDataList> getDataListUrl(@Query("urlSearch") String urlSearch);
}
