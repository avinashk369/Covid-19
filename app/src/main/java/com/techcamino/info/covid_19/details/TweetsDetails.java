package com.techcamino.info.covid_19.details;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

public class TweetsDetails implements Serializable {

    @SerializedName("text")
    private @Getter@Setter String text;
    @SerializedName("created_at")
    private @Getter@Setter String createdAt;

    @SerializedName("favorite_count")
    private @Getter@Setter String favCount;
    @SerializedName("statuses")
    private @Getter@Setter ArrayList<TweetsDetails> tweetsDetailsArrayList;
    @SerializedName("retweeted_status")
    private @Getter@Setter TweetsDetails retweetedStatus;
    @SerializedName("user")
    private @Getter@Setter UserDetails userDetails;

}
