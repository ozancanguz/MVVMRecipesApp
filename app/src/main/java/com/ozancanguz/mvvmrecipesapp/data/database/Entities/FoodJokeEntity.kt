package com.ozancanguz.mvvmrecipesapp.data.database.Entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ozancanguz.mvvmrecipesapp.models.FoodJoke
import com.ozancanguz.mvvmrecipesapp.util.Contants.Constants.Companion.FOODJOKETABLE
//1 for offline caching
@Entity(tableName = FOODJOKETABLE,)
class FoodJokeEntity(
   @Embedded
    var foodJoke: FoodJoke) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0

}
