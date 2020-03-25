package com.techcamino.info.covid_19.widgets;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class User {

    private String fullname;
    private String username;
    private boolean female;

    public User(@NonNull String fullname) {
        this.fullname = fullname;
    }

    public User(@NonNull String fullname, @NonNull String username, boolean female) {
        this.fullname = fullname;
        this.username = username;
        this.female = female;
    }

    @NonNull
    public String getFullname() {
        return fullname;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public boolean isFemale() {
        return female;
    }

    // Thanks uinames.com
    public static List<User> USERS = Arrays.asList(
            new User("India"),
            new User("Italy"),
            new User("Spain")
    );
}
