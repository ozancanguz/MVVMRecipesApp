package com.ozancanguz.mvvmrecipesapp.data.database.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ozancanguz.mvvmrecipesapp.models.Result
import com.ozancanguz.mvvmrecipesapp.util.Contants.Constants.Companion.FAVORITERECIPES_TABLE


@Entity(tableName = FAVORITERECIPES_TABLE)
class FavoritesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var result: Result
)