package com.techcamino.info.covid_19.details;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class MessageDetails implements Serializable {

    @SerializedName("id")
    private @Getter@Setter
    String id;
    @SerializedName("StatusCode")
    private @Getter@Setter
    String status_code;
    @SerializedName("message")
    private @Getter@Setter
    String message;
    @SerializedName("status")
    private @Getter@Setter
    String status;
    @SerializedName("name")
    private @Getter@Setter
    String name;


}
