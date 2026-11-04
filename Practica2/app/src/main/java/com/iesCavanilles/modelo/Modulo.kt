package com.iesCavanilles.modelo

/**
 * Representa la entidad de un módulo profesional del ciclo formativo de DAM.
 * Separa los datos de la interfaz visual para mantener una arquitectura limpia[cite: 22, 26].
 *
 * @property id Identificador único del módulo, necesario para estabilizar las listas eficientes.
 * @property nombre Nombre completo de la asignatura.
 * @property descripcion Resumen de los contenidos que se imparten.
 * @property curso Año académico correspondiente (1º DAM o 2º DAM).
 * @property horas Cantidad de horas lectivas totales del módulo.
 * @property categoria Área de aprendizaje utilizada para agrupar y filtrar en la interfaz.
 */
data class Modulo(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val curso: String,
    val horas: Int,
    val categoria: String
)