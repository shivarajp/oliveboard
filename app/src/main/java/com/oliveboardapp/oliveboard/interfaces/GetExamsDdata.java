package com.oliveboardapp.oliveboard.interfaces;

import com.oliveboardapp.oliveboard.models.ExamsResponseModel;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Shivam on 3/29/2016.
 */
public interface GetExamsDdata {

    @GET("/mocks.cgi")
    public void getData(Callback<ExamsResponseModel> responseCallback);
}
