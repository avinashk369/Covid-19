package com.techcamino.info.covid_19.details;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class SourceDetails implements Serializable {

    @SerializedName("name")
    private @Getter@Setter String name;

}
