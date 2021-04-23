package com.capgemini.retrofit

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface TMDInterface {

    @GET("3/movie/popular")
    fun getMovies(@Query("api_key") key : String): Call<PopularMovies>

    @GET
    fun getImage(@Url url : String) : Call<ResponseBody>

    companion object{
       val BASE_URL ="https://api.themoviedb.org/"
        fun getInstance(): TMDInterface
        {
           val retrobuilder= Retrofit.Builder()
            retrobuilder.addConverterFactory(GsonConverterFactory.create())
            retrobuilder.baseUrl(BASE_URL)
           val retrofit= retrobuilder.build()

            return retrofit.create(TMDInterface::class.java)
        }
    }
}