package com.iesCavanilles.ui.pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.iesCavanilles.modelo.Modulo

/**
 * Muestra el resumen general del ciclo formativo y actúa como página de bienvenida[cite: 25].
 */
@Composable
fun PantallaInicio() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Desarrollo de Aplicaciones Multiplataforma", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Aprende a desarrollar con las tecnologías más actuales en entornos de programación orientada a objetos.")
    }
}

/**
 * Presenta únicamente los módulos que el usuario ha marcado, reutilizando la lógica de la lista eficiente.
 *
 * @param modulos Colección general previamente filtrada con las selecciones del usuario.
 * @param favoritos Conjunto completo de identificadores guardados.
 * @param onAlternarFavorito Función para retirar elementos directamente desde esta vista.
 * @param onVerDetalles Función para mostrar los detalles del módulo.
 */
@Composable
fun PantallaFavoritos(
    modulos: List<Modulo>,
    favoritos: Set<Int>,
    onAlternarFavorito: (Int) -> Unit,
    onVerDetalles: (Modulo) -> Unit
) {
    if (modulos.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No tienes ningún módulo marcado como favorito.")
        }
    } else {
        ListaEficienteModulos(
            lista = modulos,
            favoritos = favoritos,
            onAlternarFavorito = onAlternarFavorito,
            onVerDetalles = onVerDetalles
        )
    }
}

/**
 * Muestra el detalle de un módulo ocupando el espacio principal.
 *
 * @param modulo Información completa del módulo a mostrar.
 * @param onVolver Función para cerrar los detalles y regresar a la vista anterior.
 */
@Composable
fun PantallaDetalleModulo(
    modulo: Modulo,
    onVolver: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Detalles de ${modulo.nombre}", style = MaterialTheme.typography.headlineMedium)
        Text(text = "Curso: ${modulo.curso}", style = MaterialTheme.typography.titleMedium)
        Text(text = "Categoría: ${modulo.categoria}", style = MaterialTheme.typography.titleMedium)
        Text(text = "Horas: ${modulo.horas}h", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = modulo.descripcion, style = MaterialTheme.typography.bodyLarge)
        
        Spacer(modifier = Modifier.weight(1f))
        
        Button(
            onClick = onVolver,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Volver")
        }
    }
}