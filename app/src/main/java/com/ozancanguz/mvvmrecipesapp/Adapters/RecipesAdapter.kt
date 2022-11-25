package com.ozancanguz.mvvmrecipesapp.Adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ozancanguz.mvvmrecipesapp.databinding.RecipesRowLayoutBinding
import com.ozancanguz.mvvmrecipesapp.models.FoodRecipe
import com.ozancanguz.mvvmrecipesapp.models.Result
import com.ozancanguz.mvvmrecipesapp.util.RecipesDiffUtil.RecipesDiffUtil


class RecipesAdapter:RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder>() {


    private var recipes= emptyList<Result>()
    // for data binding after converting recipesrowlayout as databinding layout
    class RecipesViewHolder(private val binding: RecipesRowLayoutBinding):RecyclerView.ViewHolder(binding.root) {


        fun bind(result:Result){
            binding.result = result
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val binding=RecipesRowLayoutBinding.inflate(inflater,parent,false)
        return RecipesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
      val currenRecipe=recipes[position]
      holder.bind(currenRecipe)

    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    fun setData(newData: FoodRecipe){
        val recipesDiffUtil=RecipesDiffUtil(recipes,newData.results)
        val diffutil=DiffUtil.calculateDiff(recipesDiffUtil)
        recipes=newData.results
        diffutil.dispatchUpdatesTo(this)

    }
}