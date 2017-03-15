package com.cuckoolabs.statushub.models;

/**
 * Created by insjena021 on 3/15/2017.
 */


public class Authorization {

    private String token;
    private User user;

    public Authorization(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ClassPojo [token = " + token + ", user = " + user + "]";
    }
}