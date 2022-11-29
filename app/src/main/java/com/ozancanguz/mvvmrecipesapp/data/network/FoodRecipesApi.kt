package com.ozancanguz.mvvmrecipesapp.data.network

import com.ozancanguz.mvvmrecipesapp.models.FoodRecipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface FoodRecipesApi {

    @GET("/recipes/complexSearch")
    suspend fun getRecipes(
        @QueryMap queries: Map<String, String>
    ): Response<FoodRecipe>


    @GET("/recipes/complexSearch")
    suspend fun SearchRecipes(
        @QueryMap searchQuery: Map<String, String>
    ): Response<FoodRecipe>

}