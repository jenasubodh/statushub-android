package com.cuckoolabs.statushub.models.ui;

/**
 * Created by subodhjena on 15/03/17.
 */

public class UIPost {

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getMessage() {
        return message;
    }

    public UIPost(String access_token, String message) {
        this.access_token = access_token;
        this.message = message;
    }

    private String access_token;
    private String message;

}
