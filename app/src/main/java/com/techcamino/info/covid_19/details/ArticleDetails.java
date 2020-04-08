package com.techcamino.info.covid_19.details;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

public class ArticleDetails implements Serializable {

    @SerializedName("title")
    private @Getter@Setter String title;
    @SerializedName("url")
    private @Getter@Setter String url;
    @SerializedName("urlToImage")
    private @Getter@Setter String urlToImage;
    @SerializedName("publishedAt")
    private @Getter@Setter String publishedAt;
    @SerializedName("articles")
    private @Getter@Setter ArrayList<ArticleDetails> articleDetailsArrayList;
    @SerializedName("source")
    private @Getter@Setter SourceDetails sourceDetails;

}
