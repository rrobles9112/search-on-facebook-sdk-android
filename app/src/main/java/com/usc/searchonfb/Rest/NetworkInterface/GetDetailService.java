package com.usc.searchonfb.rest.networkInterface;

import com.usc.searchonfb.rest.model.DetailModel.DetailsData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by adarsh on 4/15/2017.
 */

public interface GetDetailService {
    @GET("search_aws.php?")
    Observable<DetailsData> getDetailList(@Query("id") String id, @Query("details") String details);

}
