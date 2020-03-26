package com.techcamino.info.covid_19.resoursec;


import com.techcamino.info.covid_19.details.CountryStatsDetails;
import com.techcamino.info.covid_19.details.DashboardDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface APIInterFace {

  @GET("coronavirus/worldstat.php")
  Call<DashboardDetails> getDashboardDetails();

  @GET("coronavirus/latest_stat_by_country.php")
  Call<CountryStatsDetails> getCountryStats(@Query("country") String countryName);

}
