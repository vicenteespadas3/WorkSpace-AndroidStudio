package com.iesCavanilles.datos

import com.iesCavanilles.modelo.Modulo

/**
 * Proveedor de datos estáticos que simula una fuente de información externa o base de datos.
 * Centraliza la creación de la colección para evitar su recreación en cada recomposición visual[cite: 22, 26].
 */
object RepositorioModulos {

    /**
     * Genera la lista completa de asignaturas del ciclo formativo de DAM.
     *
     * @return Lista inmutable de objetos [Modulo] clasificados por sus áreas correspondientes.
     */
    fun obtenerCatalogo(): List<Modulo> {
        return listOf(
            Modulo(
                1,
                "Sistemas informáticos",
                "Arquitectura, hardware y software base.",
                "1º DAM",
                160,
                "Sistemas, entornos y despliegue"
            ),

            Modulo(
                2,
                "Bases de datos",
                "Diseño y manipulación de bases de datos relacionales.",
                "1º DAM",
                192,
                "Bases de datos y acceso a la información"
            ),

            Modulo(
                3,
                "Lenguajes de marcas",
                "Creación de interfaces web y estructuración de datos.",
                "1º DAM",
                96,
                "Interfaces, multimedia y experiencia de usuario"
            ),

            Modulo(
                4,
                "Programación",
                "Desarrollo de software y algoritmos base.",
                "1º DAM",
                256,
                "Programación y desarrollo de aplicaciones"
            ),

            Modulo(
                5,
                "Entornos de desarrollo",
                "Gestión de proyectos y control de versiones.",
                "1º DAM",
                96,
                "Sistemas, entornos y despliegue"
            ),

            Modulo(
                6,
                "IPE I",
                "Itinerario Personal para la Empleabilidad (I).",
                "1º DAM",
                96,
                "Empresa, empleabilidad y formación transversal"
            ),

            Modulo(
                7,
                "Inglés",
                "Inglés técnico para el sector informático.",
                "1º DAM",
                64,
                "Empresa, empleabilidad y formación transversal"
            ),

            Modulo(
                8,
                "Proyecto Intermodular (1º)",
                "Proyecto integrador de los contenidos de primer curso.",
                "1º DAM",
                32,
                "Proyectos"
            ),

            Modulo(
                9,
                "Sistemas de Gestión Empresarial",
                "Implantación y adaptación de ERPs y CRMs.",
                "2º DAM", 100,
                "Empresa, empleabilidad y formación transversal"
            ),

            Modulo(
                10,
                "IPE II",
                "Itinerario Personal para la Empleabilidad (II).",
                "2º DAM",
                100,
                "Empresa, empleabilidad y formación transversal"
            ),

            Modulo(
                11,
                "Acceso a datos",
                "Mapeo objeto-relacional (ORM) y persistencia de información.",
                "2º DAM",
                140,
                "Bases de datos y acceso a la información"
            ),

            Modulo(
                12,
                "Programación de Procesos y Servicios",
                "Hilos, concurrencia y programación en red.",
                "2º DAM",
                80,
                "Programación y desarrollo de aplicaciones"
            ),

            Modulo(
                13,
                "Programación Multimedia",
                "Desarrollo de aplicaciones móviles y videojuegos.",
                "2º DAM",
                120,
                "Programación y desarrollo de aplicaciones"
            ),

            Modulo(
                14,
                "Programación de Interfaces",
                "Diseño avanzado de interfaces gráficas y experiencia de usuario.",
                "2º DAM",
                140,
                "Interfaces, multimedia y experiencia de usuario"
            ),

            Modulo(
                15,
                "Proyecto Intermodular (2º)",
                "Proyecto final integrador de todo el ciclo formativo.",
                "2º DAM",
                40,
                "Proyectos"
            ),

            Modulo(
                16,
                "Digitalización",
                "Digitalización aplicada a los sectores productivos.",
                "2º DAM",
                30,
                "Empresa, empleabilidad y formación transversal"
            ),

            Modulo(
                17,
                "Sostenibilidad",
                "Sostenibilidad aplicada al sistema productivo.",
                "2º DAM",
                30,
                "Empresa, empleabilidad y formación transversal"
            ),

            Modulo(
                18,
                "Inglés Optativo",
                "Ampliación de competencias en inglés técnico.",
                "2º DAM",
                40,
                "Empresa, empleabilidad y formación transversal"
            )
        )
    }
}