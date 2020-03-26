package com.techcamino.info.covid_19.widgets;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.techcamino.info.covid_19.details.CountryDetails;
import com.techcamino.info.covid_19.details.MessageDetails;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class User {

    private String fullname;

    private static String affectedCountries = "\"China\",\"Italy\",\"USA\",\"Spain\",\"Germany\",\"Iran\",\"France\",\"Switzerland\",\"UK\",\"S. Korea\",\"Netherlands\",\"Austria\",\"Belgium\",\"Canada\",\"Norway\",\"Portugal\",\"Australia\",\"Brazil\",\"Sweden\",\"Israel\",\"Turkey\",\"Malaysia\",\"Denmark\",\"Czechia\",\"Ireland\",\"Luxembourg\",\"Japan\",\"Ecuador\",\"Chile\",\"Pakistan\",\"Poland\",\"Thailand\",\"Romania\",\"Saudi Arabia\",\"Indonesia\",\"Finland\",\"Russia\",\"Greece\",\"Iceland\",\"Diamond Princess\",\"South Africa\",\"Philippines\",\"India\",\"Singapore\",\"Panama\",\"Estonia\",\"Qatar\",\"Slovenia\",\"Argentina\",\"Croatia\",\"Peru\",\"Mexico\",\"Colombia\",\"Egypt\",\"Hong Kong\",\"Bahrain\",\"Dominican Republic\",\"Serbia\",\"Iraq\",\"Lebanon\",\"UAE\",\"Algeria\",\"Lithuania\",\"Armenia\",\"New Zealand\",\"Hungary\",\"Taiwan\",\"Latvia\",\"Bulgaria\",\"Slovakia\",\"Morocco\",\"Uruguay\",\"San Marino\",\"Kuwait\",\"Costa Rica\",\"Andorra\",\"Bosnia and Herzegovina\",\"North Macedonia\",\"Tunisia\",\"Jordan\",\"Ukraine\",\"Moldova\",\"Vietnam\",\"Albania\",\"Burkina Faso\",\"Faeroe Islands\",\"Cyprus\",\"Malta\",\"Réunion\",\"Brunei\",\"Oman\",\"Venezuela\",\"Senegal\",\"Sri Lanka\",\"Kazakhstan\",\"Cambodia\",\"Azerbaijan\",\"Palestine\",\"Belarus\",\"Afghanistan\",\"Ivory Coast\",\"Georgia\",\"Cameroon\",\"Guadeloupe\",\"Ghana\",\"Montenegro\",\"Martinique\",\"Uzbekistan\",\"Trinidad and Tobago\",\"Cuba\",\"Mauritius\",\"Honduras\",\"DRC\",\"Nigeria\",\"Liechtenstein\",\"Channel Islands\",\"Bangladesh\",\"Kyrgyzstan\",\"Paraguay\",\"Rwanda\",\"Bolivia\",\"Mayotte\",\"Macao\",\"Monaco\",\"French Guiana\",\"Kenya\",\"Jamaica\",\"Gibraltar\",\"French Polynesia\",\"Guatemala\",\"Isle of Man\",\"Togo\",\"Aruba\",\"Madagascar\",\"Barbados\",\"New Caledonia\",\"Uganda\",\"El Salvador\",\"Maldives\",\"Tanzania\",\"Ethiopia\",\"Zambia\",\"Djibouti\",\"Dominica\",\"Mongolia\",\"Saint Martin\",\"Equatorial Guinea\",\"Cayman Islands\",\"Haiti\",\"Suriname\",\"Gabon\",\"Niger\",\"Bermuda\",\"Namibia\",\"Seychelles\",\"Curaçao\",\"Benin\",\"Greenland\",\"Guyana\",\"Bahamas\",\"Fiji\",\"Mozambique\",\"Syria\",\"Cabo Verde\",\"Congo\",\"Eritrea\",\"Guinea\",\"Vatican City\",\"Eswatini\",\"Gambia\",\"Sudan\",\"Zimbabwe\",\"Nepal\",\"Angola\",\"Antigua and Barbuda\",\"CAR\",\"Chad\",\"Laos\",\"Liberia\",\"Mauritania\",\"Myanmar\",\"St. Barth\",\"Saint Lucia\",\"Sint Maarten\",\"Belize\",\"Bhutan\",\"British Virgin Islands\",\"Guinea-Bissau\",\"Mali\",\"Nicaragua\",\"Saint Kitts and Nevis\",\"Somalia\",\"Grenada\",\"Libya\",\"Montserrat\",\"Papua New Guinea\",\"St. Vincent Grenadines\",\"Timor-Leste\",\"Turks and Caicos\"";

    private static String countries = "[\n" +
            "{\"name\": \"China\"},{\"name\": \"Italy\"},{\"name\": \"USA\"},{\"name\": \"Spain\"},{\"name\": \"Germany\"},{\"name\": \"Iran\"},{\"name\": \"France\"},{\"name\": \"Switzerland\"},{\"name\": \"UK\"},{\"name\": \"S. Korea\"},{\"name\": \"Netherlands\"},{\"name\": \"Austria\"},{\"name\": \"Belgium\"},{\"name\": \"Canada\"},{\"name\": \"Norway\"},{\"name\": \"Portugal\"},{\"name\": \"Australia\"},{\"name\": \"Brazil\"},{\"name\": \"Sweden\"},{\"name\": \"Israel\"},{\"name\": \"Turkey\"},{\"name\": \"Malaysia\"},{\"name\": \"Denmark\"},{\"name\": \"Czechia\"},{\"name\": \"Ireland\"},{\"name\": \"Luxembourg\"},{\"name\": \"Japan\"},{\"name\": \"Ecuador\"},{\"name\": \"Chile\"},{\"name\": \"Pakistan\"},{\"name\": \"Poland\"},{\"name\": \"Thailand\"},{\"name\": \"Romania\"},{\"name\": \"Saudi Arabia\"},{\"name\": \"Indonesia\"},{\"name\": \"Finland\"},{\"name\": \"Russia\"},{\"name\": \"Greece\"},{\"name\": \"Iceland\"},{\"name\": \"Diamond Princess\"},{\"name\": \"South Africa\"},{\"name\": \"Philippines\"},{\"name\": \"India\"},{\"name\": \"Singapore\"},{\"name\": \"Panama\"},{\"name\": \"Estonia\"},{\"name\": \"Qatar\"},{\"name\": \"Slovenia\"},{\"name\": \"Argentina\"},{\"name\": \"Croatia\"},{\"name\": \"Peru\"},{\"name\": \"Mexico\"},{\"name\": \"Colombia\"},{\"name\": \"Egypt\"},{\"name\": \"Hong Kong\"},{\"name\": \"Bahrain\"},{\"name\": \"Dominican Republic\"},{\"name\": \"Serbia\"},{\"name\": \"Iraq\"},{\"name\": \"Lebanon\"},{\"name\": \"UAE\"},{\"name\": \"Algeria\"},{\"name\": \"Lithuania\"},{\"name\": \"Armenia\"},{\"name\": \"New Zealand\"},{\"name\": \"Hungary\"},{\"name\": \"Taiwan\"},{\"name\": \"Latvia\"},{\"name\": \"Bulgaria\"},{\"name\": \"Slovakia\"},{\"name\": \"Morocco\"},{\"name\": \"Uruguay\"},{\"name\": \"San Marino\"},{\"name\": \"Kuwait\"},{\"name\": \"Costa Rica\"},{\"name\": \"Andorra\"},{\"name\": \"Bosnia and Herzegovina\"},{\"name\": \"North Macedonia\"},{\"name\": \"Tunisia\"},{\"name\": \"Jordan\"},{\"name\": \"Ukraine\"},{\"name\": \"Moldova\"},{\"name\": \"Vietnam\"},{\"name\": \"Albania\"},{\"name\": \"Burkina Faso\"},{\"name\": \"Faeroe Islands\"},{\"name\": \"Cyprus\"},{\"name\": \"Malta\"},{\"name\": \"Réunion\"},{\"name\": \"Brunei\"},{\"name\": \"Oman\"},{\"name\": \"Venezuela\"},{\"name\": \"Senegal\"},{\"name\": \"Sri Lanka\"},{\"name\": \"Kazakhstan\"},{\"name\": \"Cambodia\"},{\"name\": \"Azerbaijan\"},{\"name\": \"Palestine\"},{\"name\": \"Belarus\"},{\"name\": \"Afghanistan\"},{\"name\": \"Ivory Coast\"},{\"name\": \"Georgia\"},{\"name\": \"Cameroon\"},{\"name\": \"Guadeloupe\"},{\"name\": \"Ghana\"},{\"name\": \"Montenegro\"},{\"name\": \"Martinique\"},{\"name\": \"Uzbekistan\"},{\"name\": \"Trinidad and Tobago\"},{\"name\": \"Cuba\"},{\"name\": \"Mauritius\"},{\"name\": \"Honduras\"},{\"name\": \"DRC\"},{\"name\": \"Nigeria\"},{\"name\": \"Liechtenstein\"},{\"name\": \"Channel Islands\"},{\"name\": \"Bangladesh\"},{\"name\": \"Kyrgyzstan\"},{\"name\": \"Paraguay\"},{\"name\": \"Rwanda\"},{\"name\": \"Bolivia\"},{\"name\": \"Mayotte\"},{\"name\": \"Macao\"},{\"name\": \"Monaco\"},{\"name\": \"French Guiana\"},{\"name\": \"Kenya\"},{\"name\": \"Jamaica\"},{\"name\": \"Gibraltar\"},{\"name\": \"French Polynesia\"},{\"name\": \"Guatemala\"},{\"name\": \"Isle of Man\"},{\"name\": \"Togo\"},{\"name\": \"Aruba\"},{\"name\": \"Madagascar\"},{\"name\": \"Barbados\"},{\"name\": \"New Caledonia\"},{\"name\": \"Uganda\"},{\"name\": \"El Salvador\"},{\"name\": \"Maldives\"},{\"name\": \"Tanzania\"},{\"name\": \"Ethiopia\"},{\"name\": \"Zambia\"},{\"name\": \"Djibouti\"},{\"name\": \"Dominica\"},{\"name\": \"Mongolia\"},{\"name\": \"Saint Martin\"},{\"name\": \"Equatorial Guinea\"},{\"name\": \"Cayman Islands\"},{\"name\": \"Haiti\"},{\"name\": \"Suriname\"},{\"name\": \"Gabon\"},{\"name\": \"Niger\"},{\"name\": \"Bermuda\"},{\"name\": \"Namibia\"},{\"name\": \"Seychelles\"},{\"name\": \"Curaçao\"},{\"name\": \"Benin\"},{\"name\": \"Greenland\"},{\"name\": \"Guyana\"},{\"name\": \"Bahamas\"},{\"name\": \"Fiji\"},{\"name\": \"Mozambique\"},{\"name\": \"Syria\"},{\"name\": \"Cabo Verde\"},{\"name\": \"Congo\"},{\"name\": \"Eritrea\"},{\"name\": \"Guinea\"},{\"name\": \"Vatican City\"},{\"name\": \"Eswatini\"},{\"name\": \"Gambia\"},{\"name\": \"Sudan\"},{\"name\": \"Zimbabwe\"},{\"name\": \"Nepal\"},{\"name\": \"Angola\"},{\"name\": \"Antigua and Barbuda\"},{\"name\": \"CAR\"},{\"name\": \"Chad\"},{\"name\": \"Laos\"},{\"name\": \"Liberia\"},{\"name\": \"Mauritania\"},{\"name\": \"Myanmar\"},{\"name\": \"St. Barth\"},{\"name\": \"Saint Lucia\"},{\"name\": \"Sint Maarten\"},{\"name\": \"Belize\"},{\"name\": \"Bhutan\"},{\"name\": \"British Virgin Islands\"},{\"name\": \"Guinea-Bissau\"},{\"name\": \"Mali\"},{\"name\": \"Nicaragua\"},{\"name\": \"Saint Kitts and Nevis\"},{\"name\": \"Somalia\"},{\"name\": \"Grenada\"},{\"name\": \"Libya\"},{\"name\": \"Montserrat\"},{\"name\": \"Papua New Guinea\"},{\"name\": \"St. Vincent Grenadines\"},{\"name\": \"Timor-Leste\"},{\"name\": \"Turks and Caicos\"}\n" +
            "]";

    @NonNull
    public String getFullname() {
        return fullname;
    }

    public static String castJson(){
        String[] co = affectedCountries.split(",");
        String newStr = "";
        for(int i=0;i<co.length;i++)
        {
            newStr += "{" +"\"name\": "+co[i]+"},";
        }
        return newStr;
    }

    public static ArrayList<CountryDetails> generateCountries(){
        ArrayList<CountryDetails> countryDetailsArrayList = new ArrayList<>();
        Gson gson = new Gson();
        Type type = new TypeToken<List<CountryDetails>>(){}.getType();
        countryDetailsArrayList = gson.fromJson(countries, type);

        return  countryDetailsArrayList;
    }

}
