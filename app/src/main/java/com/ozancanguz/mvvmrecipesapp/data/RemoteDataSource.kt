package com.ozancanguz.mvvmrecipesapp.data

import com.ozancanguz.mvvmrecipesapp.data.network.FoodRecipesApi
import com.ozancanguz.mvvmrecipesapp.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject


class RemoteDataSource @Inject constructor(private val foodRecipesApi: FoodRecipesApi) {

    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.getRecipes(queries)
    }

}