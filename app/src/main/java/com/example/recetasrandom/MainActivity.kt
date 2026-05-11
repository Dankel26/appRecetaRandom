package com.example.recetasrandom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
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
fun InicioApp(viewModel: RecipeViewModel = ViewModel()){

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
            onClick = { viewModel.fetchRandomRecipe() }
        ) {
            Text(text = "Me siento con suerte")
        }

        if (viewModel.isLoading){
            CircularProgressIndicator()
        }

        viewModel.recipe?.let { meal ->
            Text(
                text = meal.strMeal,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(24.dp)
            )
        }
    }
}

@Preview
@Composable
fun InicioAppPreview(){
    InicioApp()
}