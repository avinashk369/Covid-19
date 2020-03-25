package com.techcamino.info.covid_19.resoursec;


import com.techcamino.info.covid_19.util.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Thinkpad on 12-10-2016.
 */

public class APIClient {

    //public static final String BASE_URL = "http://192.168.43.193/komaki/web/index.php/";
    public static final String BASE_URL = "https://coronavirus-monitor.p.rapidapi.com/";
    private static Retrofit retrofit = null;
    private static final int processTime = 1;




    public static Retrofit getClient() {
        // Define the interceptor, add authentication headers
        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Accept","application/json")
                        .addHeader(Constants.API_KEY, Constants.API_KEY_VALUE)
//                      .addHeader("hash", generateHash())
//                      .addHeader("api_key",apiKey)
//                    //.addHeader("authtoken","authtoken")
                        .build();
                return chain.proceed(newRequest);
            }
        };

        // Add the interceptor to OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(processTime, TimeUnit.MINUTES);
        builder.connectTimeout(processTime, TimeUnit.MINUTES);
        builder.writeTimeout(processTime, TimeUnit.MINUTES);
        builder.interceptors().add(interceptor);
        OkHttpClient client = builder.build();


        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}
