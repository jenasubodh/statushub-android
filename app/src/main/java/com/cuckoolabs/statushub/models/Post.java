package com.cuckoolabs.statushub.models;

/**
 * Created by insjena021 on 3/15/2017.
 */

public class Post
{
    private String updatedAt;

    private String message;

    private String id;

    private String createdAt;

    private User user;

    public Post(String updatedAt, String message, String id, String createdAt, User user) {
        this.updatedAt = updatedAt;
        this.message = message;
        this.id = id;
        this.createdAt = createdAt;
        this.user = user;
    }

    public String getUpdatedAt ()
    {
        return updatedAt;
    }

    public void setUpdatedAt (String updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getCreatedAt ()
    {
        return createdAt;
    }

    public void setCreatedAt (String createdAt)
    {
        this.createdAt = createdAt;
    }

    public User getUser ()
    {
        return user;
    }

    public void setUser (User user)
    {
        this.user = user;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [updatedAt = "+updatedAt+", message = "+message+", id = "+id+", createdAt = "+createdAt+", user = "+user+"]";
    }
}