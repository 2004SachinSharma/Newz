package com.example.newz.data.ApiBuilder

import com.example.newz.data.apiService.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.create

class ApiBuilder {
    object ApiBuilder {
        fun retrofitObject() : ApiService {
            return Retrofit.Builder().client(OkHttpClient.Builder().build()).baseUrl("https://newsapi.org/v2/top-headlines/").addConverterFactory(GsonConverterFactory.create()).build().create(ApiService::class.java)
        }
    }
}