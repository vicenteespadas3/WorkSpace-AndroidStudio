package com.iesCavanilles

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Actividad principal de la aplicación "Gestor de perfil".
 * Contiene los campos para el nombre y correo, y botones para diversas acciones.
 */
class MainActivity : ComponentActivity() {

    private val TAG = "CicloVida"

    /**
     * Registra el resultado devuelto por PerfilActivity utilizando la Activity Result API.
     */
    private val perfilResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val nuevoNombre = result.data?.getStringExtra("NUEVO_NOMBRE")
            Toast.makeText(this, "Nombre actualizado a: $nuevoNombre", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Registra la selección de imagen utilizando GetContent.
     */
    private val seleccionarImagenLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            Toast.makeText(this, "Imagen seleccionada: $uri", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Método llamado al crear la actividad. Configura la interfaz de usuario con Jetpack Compose.
     * @param savedInstanceState Estado previamente guardado de la actividad, si existe.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "MainActivity - onCreate() ejecutado")

        setContent {
            MainScreen()
        }
    }

    /**
     * Función Composable que define la interfaz de usuario de la pantalla principal.
     * Utiliza rememberSaveable para conservar el estado ante cambios de configuración.
     */
    @Composable
    fun MainScreen() {
        var nombre by rememberSaveable { mutableStateOf("") }
        var correo by rememberSaveable { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre de usuario") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Correo electrónico") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { abrirPerfil(nombre, correo) }, modifier = Modifier.fillMaxWidth()) {
                Text("Ver perfil")
            }
            Button(onClick = { seleccionarImagen() }, modifier = Modifier.fillMaxWidth()) {
                Text("Seleccionar imagen")
            }
            Button(onClick = { abrirWeb() }, modifier = Modifier.fillMaxWidth()) {
                Text("Abrir web")
            }
            Button(onClick = { compartirPerfil(nombre, correo) }, modifier = Modifier.fillMaxWidth()) {
                Text("Compartir perfil")
            }
            Button(onClick = { abrirConfiguracion() }, modifier = Modifier.fillMaxWidth()) {
                Text("Configuración")
            }
        }
    }

    /**
     * Inicia el selector de imágenes del sistema.
     */
    private fun seleccionarImagen() {
        seleccionarImagenLauncher.launch("image/*")
    }

    /**
     * Inicia PerfilActivity enviando el nombre y correo como datos extra (Intent explícito).
     * @param nombre El nombre introducido por el usuario.
     * @param correo El correo introducido por el usuario.
     */
    private fun abrirPerfil(nombre: String, correo: String) {
        val intent = Intent(this, PerfilActivity::class.java).apply {
            putExtra("EXTRA_NOMBRE", nombre)
            putExtra("EXTRA_CORREO", correo)
        }
        perfilResultLauncher.launch(intent)
    }

    /**
     * Utiliza un Intent implícito con ACTION_VIEW para abrir una página web.
     */
    private fun abrirWeb() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com"))
        try {
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "Ninguna aplicación puede abrir este enlace", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Utiliza un Intent implícito con ACTION_SEND para compartir la información del usuario.
     * @param nombre El nombre del usuario a compartir.
     * @param correo El correo del usuario a compartir.
     */
    private fun compartirPerfil(nombre: String, correo: String) {
        val textoCompartir = "Nombre: $nombre\nCorreo: $correo"
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, textoCompartir)
        }
        val chooser = Intent.createChooser(intent, "Compartir perfil con...")
        startActivity(chooser)
    }

    /**
     * Inicia la actividad que contiene el Fragment de configuración.
     */
    private fun abrirConfiguracion() {
        val intent = Intent(this, ConfigActivity::class.java)
        startActivity(intent)
    }

    /**
     * Método del ciclo de vida llamado cuando la actividad se vuelve visible.
     */
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "MainActivity - onStart() ejecutado")
    }

    /**
     * Método del ciclo de vida llamado cuando la actividad comienza a interactuar con el usuario.
     */
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "MainActivity - onResume() ejecutado")
    }

    /**
     * Método del ciclo de vida llamado cuando la actividad pierde el foco principal.
     */
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "MainActivity - onPause() ejecutado")
    }

    /**
     * Método del ciclo de vida llamado cuando la actividad ya no es visible.
     */
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "MainActivity - onStop() ejecutado")
    }

    /**
     * Método del ciclo de vida llamado antes de que la actividad sea destruida.
     */
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "MainActivity - onDestroy() ejecutado")
    }


    @Preview
    @Composable
    fun MainScreenPreview() {
        MainScreen()
    }
}
