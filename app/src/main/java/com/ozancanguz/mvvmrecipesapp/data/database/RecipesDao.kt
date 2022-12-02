package com.ozancanguz.mvvmrecipesapp.data.database

import androidx.room.*
import com.ozancanguz.mvvmrecipesapp.data.database.Entities.FavoritesEntity
import com.ozancanguz.mvvmrecipesapp.data.database.Entities.FoodJokeEntity
import com.ozancanguz.mvvmrecipesapp.data.database.Entities.RecipesEntity
import com.ozancanguz.mvvmrecipesapp.models.FoodJoke
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipesEntity: RecipesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteRecipe(favoritesEntity: FavoritesEntity)

    @Query("SELECT * FROM recipes_table ORDER BY id ASC")
    fun readRecipes(): Flow<List<RecipesEntity>>

    @Query("SELECT * FROM favorite_recipes_table ORDER BY id ASC")
    fun readFavoriteRecipes(): Flow<List<FavoritesEntity>>

    @Delete
    suspend fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntity)

    @Query("DELETE FROM favorite_recipes_table")
    suspend fun deleteAllFavoriteRecipes()


    //2 for offline caching
    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertFoodJoke(foodJokeEntity: FoodJokeEntity)

    @Query("select* from food_joke_table Order by id  ASC")
    fun readFoodJoke():Flow<List<FoodJokeEntity>>


}