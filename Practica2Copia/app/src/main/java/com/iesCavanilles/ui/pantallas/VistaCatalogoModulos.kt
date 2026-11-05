package com.iesCavanilles.ui.pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.iesCavanilles.modelo.Modulo
import com.iesCavanilles.ui.componentes.TarjetaModuloIndividual

/**
 * Renderiza la interfaz principal del catálogo, integrando una cuadrícula de categorías y la lista de asignaturas.
 *
 * Presenta las categorías en forma de tarjetas seleccionables:
 * - La primera categoría ("Todos") ocupa todo el ancho de la pantalla.
 * - Las demás categorías se distribuyen equitativamente en una cuadrícula de dos columnas.
 *
 * @param catalogo Colección completa de módulos obtenida del repositorio.
 * @param categoriaActiva Índice numérico de la pestaña/tarjeta actualmente seleccionada.
 * @param favoritos Colección de identificadores de las asignaturas guardadas.
 * @param onCategoriaCambiada Lambda que notifica el cambio de pestaña para actualizar el estado superior.
 * @param onAlternarFavorito Lambda que añade o retira un módulo de la selección del usuario.
 * @param onVerDetalles Lambda que solicita ver los detalles del módulo.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VistaCatalogoModulos(
    catalogo: List<Modulo>,
    categoriaActiva: Int,
    favoritos: Set<Int>,
    onCategoriaCambiada: (Int) -> Unit,
    onAlternarFavorito: (Int) -> Unit,
    onVerDetalles: (Modulo) -> Unit
) {
    val categorias = listOf(
        "Todos",
        "Programación y desarrollo de aplicaciones",
        "Sistemas, entornos y despliegue",
        "Bases de datos y acceso a la información",
        "Interfaces, multimedia y experiencia de usuario",
        "Empresa, empleabilidad y formación transversal",
        "Proyectos"
    )

    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Tarjeta "Todos" (ocupa todo el ancho de la fila)
            Card(
                onClick = { onCategoriaCambiada(0) },
                modifier = Modifier.fillMaxWidth().height(64.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (categoriaActiva == 0) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = if (categoriaActiva == 0) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = categorias[0],
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            // Resto de categorías agrupadas en dos columnas
            categorias.drop(1).chunked(2).forEachIndexed { rowIndex, fila ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    fila.forEachIndexed { colIndex, texto ->
                        val indice = rowIndex * 2 + colIndex + 1
                        Card(
                            onClick = { onCategoriaCambiada(indice) },
                            modifier = Modifier.weight(1f).height(64.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (categoriaActiva == indice) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant,
                                contentColor = if (categoriaActiva == indice) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = texto,
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.labelMedium,
                                    maxLines = 3,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                    if (fila.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }

        val listaFiltrada = if (categoriaActiva == 0) {
            catalogo
        } else {
            catalogo.filter { it.categoria == categorias[categoriaActiva] }
        }

        if (listaFiltrada.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No se han encontrado módulos en esta área formativa.")
            }
        } else {
            ListaEficienteModulos(
                lista = listaFiltrada,
                favoritos = favoritos,
                onAlternarFavorito = onAlternarFavorito,
                onVerDetalles = onVerDetalles
            )
        }
    }
}

/**
 * Dibuja una lista desplazable que reutiliza las vistas visuales de forma eficiente[cite: 14, 15].
 * Aplica claves estables para identificar unívocamente cada elemento[cite: 16].
 *
 * @param lista Colección de módulos ya filtrada que debe renderizarse.
 * @param favoritos Conjunto de módulos guardados para sincronizar el estado visual de los iconos.
 * @param onAlternarFavorito Acción que propaga el evento de pulsado hacia arriba en el árbol de componentes.
 * @param onVerDetalles Acción al pulsar en el botón de ver detalles.
 */
@Composable
fun ListaEficienteModulos(
    lista: List<Modulo>,
    favoritos: Set<Int>,
    onAlternarFavorito: (Int) -> Unit,
    onVerDetalles: (Modulo) -> Unit = {}
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = lista,
            key = { modulo -> modulo.id }
        ) { modulo ->
            TarjetaModuloIndividual(
                modulo = modulo,
                esFavorito = favoritos.contains(modulo.id),
                onAlternarFavorito = { onAlternarFavorito(modulo.id) },
                onVerDetalles = { onVerDetalles(modulo) }
            )
        }
    }
}