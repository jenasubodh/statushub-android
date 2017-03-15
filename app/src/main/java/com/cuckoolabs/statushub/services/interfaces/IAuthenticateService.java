package com.cuckoolabs.statushub.services.interfaces;

import com.cuckoolabs.statushub.models.Authorization;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by subodhjena on 03/01/17.
 */
public interface IAuthenticateService {
    @POST("/auth?access_token=t3udI6F9uEc5bHDPQOjokzn9fgfA4wDq")
    Call<Authorization> basicLogin();
}
