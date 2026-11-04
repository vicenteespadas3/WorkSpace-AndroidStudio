package com.iesCavanilles

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Actividad secundaria que muestra el perfil del usuario.
 * Recibe datos de MainActivity y puede devolver resultados mediante Activity Result API.
 */
class PerfilActivity : ComponentActivity() {

    /**
     * Método llamado al crear la actividad. Recupera los extras del Intent y configura la UI.
     * @param savedInstanceState Estado previamente guardado de la actividad, si existe.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nombre = intent.getStringExtra("EXTRA_NOMBRE") ?: "Desconocido"
        val correo = intent.getStringExtra("EXTRA_CORREO") ?: "Desconocido"

        setContent {
            PerfilScreen(nombre, correo)
        }
    }

    /**
     * Función Composable que define la interfaz de visualización del perfil.
     * @param nombre Nombre recibido del Intent.
     * @param correo Correo recibido del Intent.
     */
    @Composable
    fun PerfilScreen(nombre: String, correo: String) {
        var nombreEditado by remember { mutableStateOf(nombre) }

        Column(modifier = Modifier.padding(16.dp).fillMaxSize()) {
            Text(text = "Perfil de usuario", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Nombre: $nombre")
            Text(text = "Correo: $correo")
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = nombreEditado,
                onValueChange = { nombreEditado = it },
                label = { Text("Editar nombre") }
            )

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { devolverResultado(nombreEditado) }) {
                Text("Editar perfil (Guardar)")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { finish() }) {
                Text("Volver")
            }
        }
    }

    /**
     * Configura el Intent de resultado con el nombre modificado y finaliza la actividad.
     * @param nuevoNombre El nombre modificado por el usuario que se enviará de vuelta a MainActivity.
     */
    private fun devolverResultado(nuevoNombre: String) {
        val resultIntent = Intent().apply {
            putExtra("NUEVO_NOMBRE", nuevoNombre)
        }
        setResult(RESULT_OK, resultIntent)
        finish()
    }
}