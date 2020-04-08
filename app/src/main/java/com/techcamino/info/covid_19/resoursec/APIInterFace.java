package com.techcamino.info.covid_19.resoursec;


import com.techcamino.info.covid_19.details.ArticleDetails;
import com.techcamino.info.covid_19.details.CountryStatsDetails;
import com.techcamino.info.covid_19.details.DashboardDetails;
import com.techcamino.info.covid_19.details.TweetsDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterFace {

  @GET("coronavirus/worldstat.php")
  Call<DashboardDetails> getDashboardDetails();

  @GET("coronavirus/latest_stat_by_country.php")
  Call<CountryStatsDetails> getCountryStats(@Query("country") String countryName);

  /**
   * Public twitter api to search tweets
   * @param query
   * @param resType
   * @param count
   * @return
   */
  //https://api.twitter.com/1.1/search/tweets.json?q=covid&count=100
  @GET("https://api.twitter.com/1.1/search/tweets.json")
  Call<TweetsDetails> getTweets(@Query("q") String query,
                                @Query("result_type") String resType,
                                @Query("count") int count);
  //http://newsapi.org/v2/top-headlines?country=in&apiKey=47518c7504a3421da2d999e03d918224
  @GET("http://newsapi.org/v2/top-headlines")
  Call<ArticleDetails> getNews(@Query("country") String country,
                                 @Query("apiKey") String apiKey);

}
