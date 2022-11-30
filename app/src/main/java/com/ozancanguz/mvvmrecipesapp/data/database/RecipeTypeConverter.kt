package com.ozancanguz.mvvmrecipesapp.data.database

import androidx.room.TypeConverter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ozancanguz.mvvmrecipesapp.models.FoodRecipe
import com.ozancanguz.mvvmrecipesapp.models.Result

class RecipesTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun foodRecipeToString(foodRecipe: FoodRecipe): String {
        return gson.toJson(foodRecipe)
    }

    @TypeConverter
    fun stringToFoodRecipe(data: String): FoodRecipe {
        val listType = object : TypeToken<FoodRecipe>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun resultToString(result: com.ozancanguz.mvvmrecipesapp.models.Result): String {
        return gson.toJson(result)
    }

    @TypeConverter
    fun stringToResult(data: String): com.ozancanguz.mvvmrecipesapp.models.Result {
        val listType = object : TypeToken<Result>() {}.type
        return gson.fromJson(data, listType)
    }

}