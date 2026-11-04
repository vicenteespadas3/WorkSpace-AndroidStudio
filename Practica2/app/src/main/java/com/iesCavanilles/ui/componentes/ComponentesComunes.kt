package com.iesCavanilles.ui.componentes

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Barra de aplicación superior que identifica la sección actual y proporciona acceso al menú[cite: 1, 2].
 *
 * @param titulo Texto que describe la pantalla visible en ese momento.
 * @param onAbrirMenu Función que despliega el panel de navegación lateral.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorApp(titulo: String, onAbrirMenu: () -> Unit) {
    TopAppBar(
        title = { Text(if (titulo == "Inicio") "Ciclo DAM" else titulo) },
        navigationIcon = {
            IconButton(onClick = onAbrirMenu) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Abrir menú lateral")
            }
        }
    )
}

/**
 * Construye la superficie y las opciones del menú lateral desplegable[cite: 18, 19].
 *
 * @param seccionActual Identificador de la vista activa para resaltar la opción en el menú.
 * @param onSeccionSeleccionada Función que actualiza el estado de la aplicación con la nueva ruta.
 */
@Composable
fun PanelMenuLateral(seccionActual: String, onSeccionSeleccionada: (String) -> Unit) {
    val opciones = listOf("Inicio", "Módulos", "Favoritos", "Configuración")

    ModalDrawerSheet {
        Text(
            text = "Menú DAM",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.titleLarge
        )
        opciones.forEach { opcion ->
            NavigationDrawerItem(
                label = { Text(opcion) },
                selected = seccionActual == opcion,
                onClick = { onSeccionSeleccionada(opcion) },
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        }
    }
}