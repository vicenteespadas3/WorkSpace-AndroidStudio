package com.iesCavanilles.ui.componentes

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
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
        title = { 
            Text(
                text = if (titulo == "Inicio") "Ciclo DAM" else titulo,
                fontWeight = FontWeight.Bold
            ) 
        },
        navigationIcon = {
            IconButton(onClick = onAbrirMenu) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Abrir menú lateral")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
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
    data class OpcionMenu(val titulo: String, val icono: androidx.compose.ui.graphics.vector.ImageVector)
    
    val opciones = listOf(
        OpcionMenu("Inicio", Icons.Rounded.Home),
        OpcionMenu("Módulos", Icons.AutoMirrored.Rounded.List),
        OpcionMenu("Favoritos", Icons.Rounded.Favorite),
        OpcionMenu("Configuración", Icons.Rounded.Settings)
    )

    ModalDrawerSheet(
        drawerContainerColor = MaterialTheme.colorScheme.surface,
    ) {
        Text(
            text = "Menú DAM",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        opciones.forEach { opcion ->
            NavigationDrawerItem(
                icon = { Icon(imageVector = opcion.icono, contentDescription = null) },
                label = { Text(opcion.titulo, fontWeight = FontWeight.Medium) },
                selected = seccionActual == opcion.titulo,
                onClick = { onSeccionSeleccionada(opcion.titulo) },
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                colors = androidx.compose.material3.NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }
}