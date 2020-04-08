package com.techcamino.info.covid_19.details;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class DashboardDetails implements Serializable {

    @SerializedName("total_cases")
    private @Getter@Setter
    String totalCases;
    @SerializedName("total_deaths")
    private @Getter@Setter
    String totalDeaths;
    @SerializedName("total_recovered")
    private @Getter@Setter
    String totalRecovered;
    @SerializedName("new_cases")
    private @Getter@Setter
    String newCases;
    @SerializedName("new_deaths")
    private @Getter@Setter
    String newDeaths;
    @SerializedName("statistic_taken_at")
    private @Getter@Setter
    String statsTakenAt;
    @SerializedName("record_date")
    private @Getter@Setter String recordDate;


}
