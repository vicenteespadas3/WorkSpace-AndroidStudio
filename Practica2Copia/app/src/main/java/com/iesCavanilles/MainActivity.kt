package com.iesCavanilles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.iesCavanilles.ui.navegacion.GestorNavegacionPrincipal
import com.iesCavanilles.ui.theme.Practica2_VicenteEspadasTheme

/**
 * Punto de entrada del sistema Android a la aplicación.
 * Inyecta el tema y delega todo el renderizado al gestor de navegación de Jetpack Compose.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Practica2_VicenteEspadasTheme {
                GestorNavegacionPrincipal()
            }
        }
    }
}