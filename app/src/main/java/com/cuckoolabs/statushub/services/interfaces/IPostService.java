package com.cuckoolabs.statushub.services.interfaces;

import com.cuckoolabs.statushub.models.Post;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by insjena021 on 3/15/2017.
 */

public interface IPostService {

    @GET("/posts")
    Call<List<Post>> wallpapers();
}
