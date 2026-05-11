package com.example.recetasrandom.baseService

import com.example.recetasrandom.modelData.mealResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface service {
    @GET("random.php")
    suspend fun getRandomRecipe(): mealResponse
}

object RetrofitClient {
    private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    val apiService: service by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(service::class.java)
    }
}

