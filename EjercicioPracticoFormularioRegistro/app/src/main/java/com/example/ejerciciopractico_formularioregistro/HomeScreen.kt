package com.example.ejerciciopractico_formularioregistro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Pantalla principal del formulario de registro.
 * Contiene y gestiona el estado de todos los campos (nombre, notificaciones, condiciones y sistema operativo).
 */
@Composable
fun FormularioRegistro(){
    var nombre by remember { mutableStateOf("") }
    var notificaciones by remember { mutableStateOf(false) }
    var condiciones by remember { mutableStateOf(false) }
    var sistemaOperativo by remember { mutableStateOf("Android") }

    Column(
        modifier = Modifier.fillMaxHeight(1f).fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(bottom = 30.dp).fillMaxWidth()
        ) {
            Text(
                text = "Registro de Usuario",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.twitter_blue)

            )
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(bottom = 6.dp).fillMaxWidth()
        ) {
            RegistroNombre(
                textoActual = nombre,
                alCambiarTexto = { nuevoTexto -> nombre = nuevoTexto }
            )
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            InterruptorNotificaciones(
                activado = notificaciones,
                alCambiarEstado = { nuevoEstado -> notificaciones = nuevoEstado }
            )
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            CasillaCondiciones(
                aceptado = condiciones,
                alCambiarEstado = { nuevoEstado -> condiciones = nuevoEstado }
            )
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            SeleccionSistemaOperativo(
                sistemaOperativo = sistemaOperativo,
                alCambiarTexto = { nuevoSistema -> sistemaOperativo = nuevoSistema }
            )
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            BotonRegistro(
                nombreUsuario = nombre,
                sistemaElegido = sistemaOperativo
            )
        }
    }
}


/**
 * Vista previa del formulario de registro en el entorno de diseño.
 */
@Composable
@Preview (showSystemUi = true)
fun FormularioRegistroPreview(){
    FormularioRegistro()
}


/**
 * Campo de texto para introducir el nombre de usuario.
 *
 * @param textoActual El texto actual que se muestra en el campo.
 * @param alCambiarTexto Función lambda que se ejecuta cuando el texto cambia.
 */
@Composable
fun RegistroNombre(textoActual: String, alCambiarTexto: (String) -> Unit) {
    OutlinedTextField(
        value = textoActual,
        onValueChange = alCambiarTexto,
        placeholder = { Text("Nombre de usuario") },
        label = { Text("Nombre") },
        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(id = R.color.twitter_blue),
            focusedLabelColor = colorResource(id = R.color.twitter_blue)
        )
    )
}

/**
 * Interruptor para activar o desactivar la recepción de notificaciones.
 *
 * @param activado Indica si el interruptor está activado (true) o no (false).
 * @param alCambiarEstado Función lambda que se ejecuta cuando el estado del interruptor cambia.
 */
@Composable
fun InterruptorNotificaciones(activado: Boolean, alCambiarEstado: (Boolean) -> Unit) {
    Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {



        androidx.compose.material3.Switch(
            checked = activado,
            onCheckedChange = alCambiarEstado,
            colors = androidx.compose.material3.SwitchDefaults.colors(
                checkedThumbColor = colorResource(id = R.color.twitter_blue),
                checkedTrackColor = colorResource(id = R.color.twitter_blue).copy(alpha = 0.4f)
            )
        )

        Text(
            text = "Recibir notificaciones",
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

/**
 * Casilla de verificación (Checkbox) para aceptar los términos y condiciones.
 *
 * @param aceptado Indica si la casilla está marcada (true) o no (false).
 * @param alCambiarEstado Función lambda que se ejecuta cuando la casilla se marca o desmarca.
 */
@Composable
fun CasillaCondiciones(aceptado:Boolean, alCambiarEstado: (Boolean) -> Unit){
    Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically){
        androidx.compose.material3.Checkbox(
            checked = aceptado,
            onCheckedChange = alCambiarEstado,
            colors = androidx.compose.material3.CheckboxDefaults.colors(
                checkedColor = colorResource(id = R.color.twitter_blue)
            )
        )
        Text(
            text = "He leído y acepto los términos y condiciones",
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}


/**
 * Grupo de botones de radio (RadioButtons) para seleccionar el sistema operativo favorito.
 *
 * @param sistemaOperativo El nombre del sistema operativo actualmente seleccionado.
 * @param alCambiarTexto Función lambda que se ejecuta al seleccionar una nueva opción.
 */
@Composable
fun SeleccionSistemaOperativo(sistemaOperativo: String, alCambiarTexto: (String) -> Unit) {
    Column(modifier = androidx.compose.ui.Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text(
            text = "Selecciona el sistema operativo:",
            fontWeight = FontWeight.Bold,
            modifier = androidx.compose.ui.Modifier.padding(bottom = 4.dp)
        )

        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically){
            androidx.compose.material3.RadioButton(
                selected = sistemaOperativo == "Android",
                onClick = { alCambiarTexto("Android") },
                colors = androidx.compose.material3.RadioButtonDefaults.colors(
                    selectedColor = colorResource(id = R.color.twitter_blue)
                )
            )
            Text(
                text = "Android",
            )

        }

        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically){
            androidx.compose.material3.RadioButton(
                selected = sistemaOperativo == "iOS",
                onClick = { alCambiarTexto("iOS") },
                colors = androidx.compose.material3.RadioButtonDefaults.colors(
                    selectedColor = colorResource(id = R.color.twitter_blue)
                )
            )
            Text(
                text = "iOS",
            )
        }
    }
}


/**
 * Botón para enviar o procesar el formulario de registro.
 *
 * @param nombreUsuario El nombre introducido en el formulario.
 * @param sistemaElegido El sistema operativo seleccionado en el formulario.
 */
@Composable
fun BotonRegistro(nombreUsuario: String, sistemaElegido:String){
    androidx.compose.material3.Button(
        onClick = {
            println("Usuario: $nombreUsuario")
            println("Sistema elegido: $sistemaElegido")
        },
        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.twitter_blue)
        )
    ){
        Text(text="Registrar")
    }
}








