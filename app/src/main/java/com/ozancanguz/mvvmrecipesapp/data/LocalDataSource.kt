package com.ozancanguz.mvvmrecipesapp.data

import com.ozancanguz.mvvmrecipesapp.data.database.RecipesDao
import com.ozancanguz.mvvmrecipesapp.data.database.Entities.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipesDao: RecipesDao
) {

    fun readDatabase(): Flow<List<RecipesEntity>> {
        return recipesDao.readRecipes()
    }

    suspend fun insertRecipes(recipesEntity: RecipesEntity) {
        recipesDao.insertRecipes(recipesEntity)
    }

}