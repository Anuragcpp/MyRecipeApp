package com.example.myrcipeapp

import android.content.ContentValues.TAG
import android.util.Log
import androidx.collection.longLongMapOf
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel  : ViewModel(){

    //This is the private variable usig the mutable state
    private val _categoryState = mutableStateOf(RecipeState())
    // to make the category avalilble to the global, State type -> android.compose.lifecycle
    val categoryState :  State<RecipeState> = _categoryState

    init {
        fetchCategories()
    }

    private fun fetchCategories () {
        viewModelScope.launch {
            try {
                val response = recipeService.getCategories()
                _categoryState.value = _categoryState.value.copy(
                    loading = false,
                    list = response.categories,
                    error = null
                )
                Log.i(TAG, "response get successfully")

            }catch (e : Exception){
                _categoryState.value = _categoryState.value.copy(
                    loading = false,
                    error = "Error fetching categories ${e.message}"
                )
                Log.i(TAG, "response is  Unsuccessfully")
            }
        }
    }


    data class RecipeState (
        var loading : Boolean = true,
        var list : List<Category> = emptyList(),
        var error : String? = null
    )
}