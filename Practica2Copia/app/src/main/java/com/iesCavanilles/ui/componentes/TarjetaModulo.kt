package com.iesCavanilles.ui.componentes

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.iesCavanilles.modelo.Modulo

/**
 * Componente visual reutilizable que agrupa y presenta la información de un único módulo profesional.
 * Utiliza [Card] para proporcionar una superficie diferenciada con elevación[cite: 10, 11].
 *
 * @param modulo Instancia con los datos que se deben mostrar.
 * @param esFavorito Indica si el módulo está guardado para alternar el icono visual.
 * @param onAlternarFavorito Acción que se ejecuta al pulsar el botón de guardado.
 * @param onVerDetalles Acción que se ejecuta para ver los contenidos en profundidad.
 */
@Composable
fun TarjetaModuloIndividual(
    modulo: Modulo,
    esFavorito: Boolean,
    onAlternarFavorito: () -> Unit,
    onVerDetalles: () -> Unit = {}
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = modulo.nombre,
                    style = MaterialTheme.typography.titleMedium
                )
                IconButton(onClick = onAlternarFavorito) {
                    Icon(
                        imageVector = if (esFavorito) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Marcar como favorito"
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = modulo.descripcion, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${modulo.curso} | ${modulo.horas} horas lectivas",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = onVerDetalles) {
                Text("Ver contenidos del módulo")
            }
        }
    }
}