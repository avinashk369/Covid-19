package com.techcamino.info.covid_19.details;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

public class CountryStatsDetails implements Serializable {

    @SerializedName("country")
    private @Getter@Setter String country;
    @SerializedName("latest_stat_by_country")
    private @Getter@Setter ArrayList<DashboardDetails> latestStat;
    @SerializedName("id")
    private @Getter@Setter String id;
    @SerializedName("country_name")
    private @Getter@Setter String countryName;
    @SerializedName("new_deaths")
    private @Getter@Setter String newDeaths;
    @SerializedName("statistic_taken_at")
    private @Getter@Setter String statsTakenAt;
    @SerializedName("total_cases")
    private @Getter@Setter String totalCases;
    @SerializedName("new_cases")
    private @Getter@Setter String newCases;
    @SerializedName("active_cases")
    private @Getter@Setter String activeCases;
    @SerializedName("total_deaths")
    private @Getter@Setter String totalDeath;
    @SerializedName("total_recovered")
    private @Getter@Setter String totalRecovered;
    @SerializedName("serious_critical")
    private @Getter@Setter String seriousCritical;
    @SerializedName("region")
    private @Getter@Setter String region;
    @SerializedName("total_cases_per1m")
    private @Getter@Setter String totalCasesPer1m;
    @SerializedName("record_date")
    private @Getter@Setter String recordDate;

}
