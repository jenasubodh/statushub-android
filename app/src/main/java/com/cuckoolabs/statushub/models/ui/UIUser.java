package com.cuckoolabs.statushub.models.ui;

/**
 * Created by insjena021 on 3/15/2017.
 */

public class UIUser {

    private String email;
    private String name;
    private String password;

    public UIUser(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ClassPojo [email = " + email + ", name = " + name + ", password = " + password + "]";
    }
}
