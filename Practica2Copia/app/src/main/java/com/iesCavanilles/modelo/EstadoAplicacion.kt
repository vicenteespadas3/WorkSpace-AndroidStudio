package com.iesCavanilles.modelo

/**
 * Agrupa el estado global de la interfaz para mantener sincronizados los controles y el contenido[cite: 22, 26].
 *
 * @property seccionActiva Nombre de la opción seleccionada actualmente en el menú lateral.
 * @property categoriaSeleccionada Índice numérico de la pestaña activa en el catálogo de módulos.
 * @property favoritos Conjunto de identificadores de los módulos que el usuario ha marcado.
 * @property moduloSeleccionadoId Identificador del módulo del que se están viendo los detalles. Null si no hay ninguno seleccionado.
 */
data class EstadoAplicacion(
    val seccionActiva: String = "Inicio",
    val categoriaSeleccionada: Int = 0,
    val favoritos: Set<Int> = emptySet(),
    val moduloSeleccionadoId: Int? = null
)