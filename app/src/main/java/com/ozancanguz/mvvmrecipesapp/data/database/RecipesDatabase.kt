package com.ozancanguz.mvvmrecipesapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ozancanguz.mvvmrecipesapp.data.database.Entities.FavoritesEntity
import com.ozancanguz.mvvmrecipesapp.data.database.Entities.RecipesEntity

@Database(
    entities = [RecipesEntity::class,FavoritesEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RecipesTypeConverter::class)
abstract class RecipesDatabase: RoomDatabase() {

    abstract fun recipesDao(): RecipesDao

}