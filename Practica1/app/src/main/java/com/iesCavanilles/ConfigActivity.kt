package com.iesCavanilles

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

/**
 * Actividad encargada de mostrar y gestionar la pantalla de configuración de la aplicación.
 * Reemplaza la implementación basada en Fragments utilizando exclusivamente Jetpack Compose
 * para mantener la coherencia visual con Material 3.
 */
class ConfigActivity : ComponentActivity() {

    /**
     * Método del ciclo de vida llamado al crear la actividad.
     * Configura la jerarquía de vistas utilizando Jetpack Compose, aplicando el tema
     * principal y definiendo la acción a realizar al guardar los datos.
     *
     * @param savedInstanceState Estado previamente guardado de la actividad, si existe.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ConfigScreen(
                        onGuardar = { notificaciones, modo ->
                            guardarConfiguracion(notificaciones, modo)
                        }
                    )
                }
            }
        }
    }

    /**
     * Procesa la acción de guardado de la configuración seleccionada por el usuario.
     * Muestra un mensaje de confirmación mediante un Toast y finaliza la actividad
     * para retornar a la pantalla principal.
     *
     * @param notificaciones Valor booleano que indica si el usuario desea recibir notificaciones.
     * @param modo Cadena de texto que representa el modo de visualización seleccionado.
     */
    private fun guardarConfiguracion(notificaciones: Boolean, modo: String) {
        Toast.makeText(
            this,
            "Configuración guardada",
            Toast.LENGTH_SHORT
        ).show()
        finish()
    }
}

/**
 * Función Composable que construye la interfaz gráfica de la pantalla de configuración.
 * Integra un interruptor para notificaciones, un grupo de botones de radio para el tema visual
 * y un botón para confirmar los cambios.
 *
 * @param onGuardar Función de orden superior que se invoca al pulsar el botón de guardar,
 *                  pasando como parámetros el estado del Switch y el modo de visualización elegido.
 */
@Composable
fun ConfigScreen(onGuardar: (Boolean, String) -> Unit) {
    var notificacionesActivadas by remember { mutableStateOf(false) }
    val opcionesModo = listOf("Claro", "Oscuro", "Sistema")
    var modoSeleccionado by remember { mutableStateOf(opcionesModo[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Configuración",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Activar notificaciones",
                style = MaterialTheme.typography.bodyLarge
            )
            Switch(
                checked = notificacionesActivadas,
                onCheckedChange = { notificacionesActivadas = it }
            )
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        Text(
            text = "Modo de visualización",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Column(modifier = Modifier.selectableGroup()) {
            opcionesModo.forEach { modo ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .selectable(
                            selected = (modo == modoSeleccionado),
                            onClick = { modoSeleccionado = modo },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (modo == modoSeleccionado),
                        onClick = null
                    )
                    Text(
                        text = modo,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { onGuardar(notificacionesActivadas, modoSeleccionado) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar configuración")
        }
    }
}