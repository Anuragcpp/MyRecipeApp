package com.example.myrcipeapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


// creating a retrofit object to create a connection with the  base url
private val retrofit = Retrofit
    .Builder()
    .baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val recipeService = retrofit.create(ApiServices::class.java)

interface ApiServices {

    @GET("categories.php")
    suspend fun getCategories() : CategroyResponse
}