package com.example.newz.data.apiService

import com.example.newz.data.model.ApiResponse

import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService { // making an API Service interface

    @GET("top-headlines") // @GET annotation is used here, to get data from the API , similarly more Annotations are there like @POST, @PUT, @UPDATE, @PATCH and more , these are called the methods is restful API
    suspend fun getHeadLines(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = "8e7e2605794f4944ae72eda9a983def2"
    ): ApiResponse
    @GET("everything") // @GET annotation is used here, to get data from the API , similarly more Annotations are there like @POST, @PUT, @UPDATE, @PATCH and more , these are called the methods is restful API
    suspend fun getEverything(
        @Query("q") q: String = "us",
        @Query("apiKey") apiKey: String = "8e7e2605794f4944ae72eda9a983def2"
    ): ApiResponse




}

