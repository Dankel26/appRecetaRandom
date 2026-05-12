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
fun fetchRandomRecipe(load: (data: Any)-> Unit){ // load, carga los datos de data con el tipo de dato any para llevarlos a otra actividad
    viewModelScope.launch { isLoading = true
    try {
        val response = RetrofitClient.apiService.getRandomRecipe()
        val boyd = response.meals // captura los datos y los guarda en boyd
        println("okkkk") // mensaje de que funciona
        println(boyd.size) // retorna el tamaño del array
        println(boyd) // imprime el array
        load(boyd) // los carga en el callback, y luego se guarda en el parametro de la clase
    }catch (e: Exception){
        //Errores
        e.printStackTrace()
        println(e) // imprime el error
    }finally {
        isLoading = false
    }
    }
}
}