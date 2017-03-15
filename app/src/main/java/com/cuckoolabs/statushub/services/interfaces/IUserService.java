package com.cuckoolabs.statushub.services.interfaces;

import com.cuckoolabs.statushub.models.User;
import com.cuckoolabs.statushub.models.ui.UIUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by insjena021 on 3/15/2017.
 */

public interface IUserService {

    @POST("/users?access_token=t3udI6F9uEc5bHDPQOjokzn9fgfA4wDq")
    Call<User> createUser(@Body UIUser user);
}
