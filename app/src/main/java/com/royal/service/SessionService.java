package com.royal.service;

import com.royal.model.ResponseModel;
import com.royal.model.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SessionService {


     //api
    //url
    //input ?
    //response ?
    //http method?
  @POST("/api/auth/signup")
   Call<ResponseModel> signupApi(@Body UserModel userModel);

  @POST("/api/auth/login")
  Call<ResponseModel> loginApi(@Body UserModel userModel); //LoginModel

}
