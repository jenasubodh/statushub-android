package com.cuckoolabs.statushub.services.interfaces;

import com.cuckoolabs.statushub.models.Post;
import com.cuckoolabs.statushub.models.User;
import com.cuckoolabs.statushub.models.ui.UIPost;
import com.cuckoolabs.statushub.models.ui.UIUser;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by insjena021 on 3/15/2017.
 */

public interface IPostService {

    @POST("/posts")
    Call<Post> createPost(@Body UIPost post);
}
