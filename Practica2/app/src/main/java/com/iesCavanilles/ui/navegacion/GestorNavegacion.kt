package com.iesCavanilles.ui.navegacion

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.iesCavanilles.datos.RepositorioModulos
import com.iesCavanilles.modelo.EstadoAplicacion
import com.iesCavanilles.ui.componentes.BarraSuperiorApp
import com.iesCavanilles.ui.componentes.PanelMenuLateral
import com.iesCavanilles.ui.pantallas.PantallaDetalleModulo
import com.iesCavanilles.ui.pantallas.PantallaFavoritos
import com.iesCavanilles.ui.pantallas.PantallaInicio
import com.iesCavanilles.ui.pantallas.VistaCatalogoModulos
import kotlinx.coroutines.launch

/**
 * Orquesta la estructura base de la aplicación y el enrutamiento de componentes[cite: 26].
 * Mantiene el estado persistente y organiza las áreas generales con Scaffold y ModalNavigationDrawer[cite: 3, 18].
 */
@Composable
fun GestorNavegacionPrincipal() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var estadoApp by remember { mutableStateOf(EstadoAplicacion()) }
    val catalogo = remember { RepositorioModulos.obtenerCatalogo() }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            PanelMenuLateral(
                seccionActual = estadoApp.seccionActiva,
                onSeccionSeleccionada = { nuevaSeccion ->
                    estadoApp = estadoApp.copy(seccionActiva = nuevaSeccion)
                    scope.launch { drawerState.close() }
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                BarraSuperiorApp(
                    titulo = estadoApp.seccionActiva,
                    onAbrirMenu = { scope.launch { drawerState.open() } }
                )
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding) // Evita que la barra cubra el contenido[cite: 4]
            ) {
                if (estadoApp.moduloSeleccionadoId != null) {
                    val modulo = catalogo.find { it.id == estadoApp.moduloSeleccionadoId }
                    if (modulo != null) {
                        PantallaDetalleModulo(
                            modulo = modulo,
                            onVolver = { estadoApp = estadoApp.copy(moduloSeleccionadoId = null) }
                        )
                    }
                } else {
                    when (estadoApp.seccionActiva) {
                        "Inicio" -> PantallaInicio()
                        "Módulos" -> VistaCatalogoModulos(
                            catalogo = catalogo,
                            categoriaActiva = estadoApp.categoriaSeleccionada,
                            favoritos = estadoApp.favoritos,
                            onCategoriaCambiada = { nuevaCategoria ->
                                estadoApp = estadoApp.copy(categoriaSeleccionada = nuevaCategoria)
                            },
                            onAlternarFavorito = { id ->
                                val nuevosFavoritos = if (estadoApp.favoritos.contains(id)) {
                                    estadoApp.favoritos - id
                                } else {
                                    estadoApp.favoritos + id
                                }
                                estadoApp = estadoApp.copy(favoritos = nuevosFavoritos)
                            },
                            onVerDetalles = { modulo ->
                                estadoApp = estadoApp.copy(moduloSeleccionadoId = modulo.id)
                            }
                        )
                        "Favoritos" -> PantallaFavoritos(
                            modulos = catalogo.filter { estadoApp.favoritos.contains(it.id) },
                            favoritos = estadoApp.favoritos,
                            onAlternarFavorito = { id ->
                                estadoApp = estadoApp.copy(favoritos = estadoApp.favoritos - id)
                            },
                            onVerDetalles = { modulo ->
                                estadoApp = estadoApp.copy(moduloSeleccionadoId = modulo.id)
                            }
                        )
                        "Configuración" -> Text("Ajustes de la aplicación", modifier = Modifier.align(Alignment.Center))
                    }
                }
            }
        }
    }
}