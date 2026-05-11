package com.example.recetasrandom.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recetasrandom.baseService.RetrofitClient
import com.example.recetasrandom.modelData.Meal
import kotlinx.coroutines.launch


class RecipeViewModel : ViewModel() {
    // Estado de la receta
   var recipe by mutableStateOf<Meal?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set
//LOGICA PARA LA CONEXION DE LA BASE DE DATOS
fun fetchRandomRecipe(){
    viewModelScope.launch { isLoading = true
    try {
        val response = RetrofitClient.apiService.getRandomRecipe()
    }catch (e: Exception){
        //Errores
    }finally {
        isLoading = false
    }
    }
}
}