package com.techcamino.info.covid_19.resoursec;



import com.techcamino.info.covid_19.details.DashboardDetails;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Thinkpad on 12-10-2016.
 */

public interface APIInterFace {


    /*@POST("user/login")
    Call<UserDetails> userLogin(@Body UserDetails user);
*/

  /*  @POST("user/addDealer")
    @FormUrlEncoded
    Call<UserDetails> saveDealer(@FieldMap Map<String, String> dealerMap);
*/
    @GET("coronavirus/worldstat.php")
    Call<DashboardDetails> getDashboardDetails();

  /*  @POST("warranty/deleteMap")
    @FormUrlEncoded
    Call<String> deletePackageMap(@Field("package_id") String packageId);*/
}
