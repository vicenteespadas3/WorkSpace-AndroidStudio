package com.example.ejerciciopractico_formularioregistro

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ejerciciopractico_formularioregistro.ui.theme.EjercicioPracticoFormularioRegistroTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjercicioPracticoFormularioRegistroTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    // Llamamos a nuestro componente principal dentro de un Surface/Scaffold
                    FormularioRegistro()
                }
            }
        }
    }
}

