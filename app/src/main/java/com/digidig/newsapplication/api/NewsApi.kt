package com.digidig.newsapplication.api

import com.digidig.newsapplication.model.NewsResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/everything")
    fun getBreakingNews(
        @Query("q")
        countryCode: String = "bitcoin",
        @Query("pageSize")
        pageSize:Int = 7,
        @Query("page")
        pageNum : Int = 1,
        @Query("apiKey")
        apiKey : String = "ca5d2490479844d989f815d43a750667"
    ) : Call<NewsResponse>
}