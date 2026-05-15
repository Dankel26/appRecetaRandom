package com.example.recetasrandom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recetasrandom.modelData.Meal
import com.example.recetasrandom.ui.theme.RecetasRandomTheme
import com.example.recetasrandom.viewModel.RecipeViewModel
import kotlin.getValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           InicioApp()
        }
    }
}
@Composable
fun InicioApp(viewModel: RecipeViewModel = viewModel()){ // callback de la clase

    Column(
        modifier = Modifier.padding(50.dp).fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        )
    {
        Spacer(modifier = Modifier.height(300.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            style = MaterialTheme.typography.headlineMedium,
            text = "Recetas Random",
        )
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Presiona el botón para obtener una deliciosa receta seleccionada al azar.",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant // Un color un poco más suave
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.fetchRandomRecipe(load = { //callback
                println(it) // trae los datos capturados en el viewmodel
            })}
        ) {
            Text(text = "Me siento con suerte")
        }

        if (viewModel.isLoading){
            CircularProgressIndicator()
        }
        viewModel.recipe?.let { meal ->
            //llamar al Composable de tablas
            tablaRecetas(meals = listOf(meal)) // pasa como parametro los datos de <meal> en una lista. callback
//            Text(
//                text = meal.strMeal,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier.padding(24.dp)
//            )
        }


    }
}

@Preview
@Composable
fun InicioAppPreview(){
    InicioApp()
}

@Composable
fun tablaRecetas(meals: List<Meal>){ //trae los datos de <meal> callback
    // Definimos las medidas (proporciones) de cada columna
    val tamaId = 0.6f
    val tamaNombre = 1.4f
    val tamaInstrucciones = 3.0f // Le damos más espacio a las instrucciones

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(1.dp, Color.LightGray) // Borde exterior de la tabla
    ) {
        // 1. ENCABEZADO DE LA TABLA
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primaryContainer) // Color destacado para el título
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "ID", modifier = Modifier.weight(tamaId), fontWeight = FontWeight.Bold)
                Text(text = "Nombre", modifier = Modifier.weight(tamaNombre), fontWeight = FontWeight.Bold)
                Text(text = "Instrucciones", modifier = Modifier.weight(tamaInstrucciones), fontWeight = FontWeight.Bold)
            }
        }

        // 2. FILAS DE DATOS (Contenido)
        items(meals) { meal ->
            // Línea divisoria entre filas
            HorizontalDivider(color = Color.LightGray, thickness = 1.dp)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.Top // Alinea el texto al inicio de la celda
            ) {
                // Columna ID
                Text(text = meal.idMeal, modifier = Modifier.weight(tamaId))

                // Columna Nombre
                Text(text = meal.strMeal, modifier = Modifier.weight(tamaNombre), fontWeight = FontWeight.Medium)

                // Columna Instrucciones (Controlando el texto largo)
                Text(
                    text = meal.strInstructions,
                    modifier = Modifier.weight(tamaInstrucciones),
                    maxLines = 3, // Limita a 3 líneas para que no deforme la fila
                    overflow = TextOverflow.Ellipsis // Agrega "..." si el texto es muy largo
                )
            }
        }
    }
}
@Preview
@Composable
fun TablaRecetasPreview(){
    val meal = Meal(
        idMeal ="1",
        strMeal = "receta prueba",
        strInstructions = "instrucciones de prueba",
        strMealThumb = "imagen de prueba"
    )

    tablaRecetas(meals = listOf(meal))
}