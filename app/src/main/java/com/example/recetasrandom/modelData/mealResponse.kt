package com.example.recetasrandom.modelData

class mealResponse (val meals: List<Meal>)
data class Meal(
    val idMeal: String,
    val strMeal: String,        // Nombre de la receta
    val strInstructions: String, // Instrucciones
    val strMealThumb: String    // URL de la imagen
)
