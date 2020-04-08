package com.techcamino.info.covid_19.details;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

public class UserDetails implements Serializable {

    @SerializedName("name")
    private @Getter@Setter String name;
    @SerializedName("screen_name")
    private @Getter@Setter String screen_name;
    @SerializedName("profile_image_url")
    private @Getter@Setter String profileImageUrl;


}
