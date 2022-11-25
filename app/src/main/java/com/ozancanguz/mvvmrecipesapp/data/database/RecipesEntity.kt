package com.ozancanguz.mvvmrecipesapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ozancanguz.mvvmrecipesapp.models.FoodRecipe
import com.ozancanguz.mvvmrecipesapp.util.Contants.Constants.Companion.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(
    var foodRecipe: FoodRecipe
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}