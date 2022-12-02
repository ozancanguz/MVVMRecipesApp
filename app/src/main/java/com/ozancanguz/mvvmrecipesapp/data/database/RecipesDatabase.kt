package com.ozancanguz.mvvmrecipesapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ozancanguz.mvvmrecipesapp.data.database.Entities.FavoritesEntity
import com.ozancanguz.mvvmrecipesapp.data.database.Entities.FoodJokeEntity
import com.ozancanguz.mvvmrecipesapp.data.database.Entities.RecipesEntity

@Database(
    //3 for offline caching
    entities = [RecipesEntity::class,FavoritesEntity::class,FoodJokeEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(RecipesTypeConverter::class)
abstract class RecipesDatabase: RoomDatabase() {

    abstract fun recipesDao(): RecipesDao

}