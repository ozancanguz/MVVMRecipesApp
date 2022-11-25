package com.ozancanguz.mvvmrecipesapp.ui.fragments.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ozancanguz.mvvmrecipesapp.Adapters.RecipesAdapter
import com.ozancanguz.mvvmrecipesapp.R
import com.ozancanguz.mvvmrecipesapp.util.NetworkResult
import com.ozancanguz.mvvmrecipesapp.viewmodels.MainViewModel
import com.ozancanguz.mvvmrecipesapp.viewmodels.RecipesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_recipes.view.*

@AndroidEntryPoint
class RecipesFragment : Fragment() {


    private lateinit var mView:View
    private val mAdapter by lazy{RecipesAdapter()}
    private lateinit var mainviewModel:MainViewModel
    private lateinit var recipesViewModel:RecipesViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // init viewmodel
        mainviewModel= ViewModelProvider(this)[MainViewModel::class.java]
        recipesViewModel = ViewModelProvider(requireActivity()).get(RecipesViewModel::class.java)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       mView=inflater.inflate(R.layout.fragment_recipes, container, false)

        //set up rv
        setRecyclerView()

        requestApiData()





        return mView
    }
    private fun requestApiData() {
        mainviewModel.getRecipes(recipesViewModel.applyQueries())
        mainviewModel.recipesResponse.observe(viewLifecycleOwner, { response ->
            when(response){
                is NetworkResult.Success -> {

                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {

                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading ->{

                }
            }
        })
    }


    private fun setRecyclerView(){
        mView.recyclerview.adapter=mAdapter
        mView.recyclerview.layoutManager=LinearLayoutManager(requireContext())


    }



}