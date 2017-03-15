package com.cuckoolabs.statushub.models;

/**
 * Created by insjena021 on 3/15/2017.
 */

public class User {
    private String picture;

    private String id;

    private String name;

    public User(String picture, String id, String name) {
        this.picture = picture;
        this.id = id;
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ClassPojo [picture = " + picture + ", id = " + id + ", name = " + name + "]";
    }
}

