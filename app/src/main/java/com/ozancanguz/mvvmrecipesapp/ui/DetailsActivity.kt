package com.ozancanguz.mvvmrecipesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navArgs
import com.google.android.material.snackbar.Snackbar
import com.ozancanguz.mvvmrecipesapp.Adapters.PagerAdapter
import com.ozancanguz.mvvmrecipesapp.R
import com.ozancanguz.mvvmrecipesapp.data.database.Entities.FavoritesEntity
import com.ozancanguz.mvvmrecipesapp.ui.fragments.ingredients.IngredientsFragment
import com.ozancanguz.mvvmrecipesapp.ui.fragments.instructions.InstructionsFragment
import com.ozancanguz.mvvmrecipesapp.ui.fragments.overview.OverviewFragment
import com.ozancanguz.mvvmrecipesapp.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_details.*

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    private val args by navArgs<DetailsActivityArgs>()

    private var recipeSaved=false
    private var savedRecipeId=0


    private lateinit var mainViewModel:MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

       val fragments=ArrayList<Fragment>()
        fragments.add(OverviewFragment())
        fragments.add(IngredientsFragment())
        fragments.add(InstructionsFragment())

        val titles=ArrayList<String>()
        titles.add("Overview")
        titles.add("Ingredients")
        titles.add("Instructions")


        val resultBundle=Bundle()
        resultBundle.putParcelable("recipeBundle", args.result)

        val adapter=PagerAdapter(
            resultBundle,
            fragments,
            titles,
            supportFragmentManager

        )
        viewPager.adapter=adapter
        tabLayout.setupWithViewPager(viewPager)

        mainViewModel= ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        } else if (item.itemId == R.id.savetofavmenu && !recipeSaved) {
            saveToFavorites(item)
        } else if (item.itemId == R.id.savetofavmenu && recipeSaved) {
            removeFromFavorites(item)
        }
        return super.onOptionsItemSelected(item)
    }


    private fun saveToFavorites(item: MenuItem) {
               val favoritesEntity=FavoritesEntity(0,args.result)

         mainViewModel.insertFavoriteRecipe(favoritesEntity)

        changeMenuItemColor(item,R.color.yellow)

        Snackbar.make(detailsLayout,"Recipe Saved",Snackbar.LENGTH_LONG)
            .setAction("Okay"){}
            .show()

        recipeSaved=true

    }
    private fun removeFromFavorites(item: MenuItem) {
        val favoritesEntity =
            FavoritesEntity(
                savedRecipeId,
                args.result
            )
        mainViewModel.deleteFavoriteRecipe(favoritesEntity)
        changeMenuItemColor(item, R.color.white)

        Snackbar.make(detailsLayout,"Recipe Deleted",Snackbar.LENGTH_LONG)
            .setAction("Okay"){}
            .show()
        recipeSaved = false
    }





    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_menu,menu)

        val menuItem = menu?.findItem(R.id.savetofavmenu)
        checkSavedRecipes(menuItem!!)
        return true
    }

    private fun checkSavedRecipes(menuItem: MenuItem) {
        mainViewModel.readFavoriteRecipes.observe(this, { favoritesEntity ->
            try {
                for (savedRecipe in favoritesEntity) {
                    if (savedRecipe.result.id == args.result.id) {
                        changeMenuItemColor(menuItem, R.color.yellow)
                        savedRecipeId = savedRecipe.id
                        recipeSaved = true
                    } else {
                        changeMenuItemColor(menuItem, R.color.white)
                    }
                }
            } catch (e: Exception) {
                Log.d("DetailsActivity", e.message.toString())
            }
        })
    }

    private fun changeMenuItemColor(item: MenuItem, yellow: Int) {

        item.icon?.setTint(ContextCompat.getColor(this,yellow))
    }

}