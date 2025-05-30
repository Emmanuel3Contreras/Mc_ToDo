package com.example.mc_todo // Asegúrate de que este sea el nombre correcto de tu paquete

import java.util.Date // Para la fecha de vencimiento

/**
 * Clase de datos que representa una tarea individual.
 * @param id Identificador único de la tarea.
 * @param title Título principal de la tarea.
 * @param description Descripción detallada de la tarea (opcional).
 * @param isCompleted Indica si la tarea ha sido completada.
 * @param dueDate Fecha y hora de vencimiento de la tarea (opcional).
 * @param priority Nivel de prioridad de la tarea (ej. 1 para alta, 3 para baja).
 * @param order Posición de la tarea en la lista (para reordenamiento manual).
 */
data class Task(
    val id: String = java.util.UUID.randomUUID().toString(), // Genera un ID único por defecto
    var title: String,
    var description: String = "", // Descripción opcional, por defecto vacía
    var isCompleted: Boolean = false, // Por defecto, la tarea no está completada
    var dueDate: Date? = null, // Fecha de vencimiento opcional
    var priority: Int = 0, // Prioridad, 0 por defecto (o puedes definir un enum)
    var order: Int = 0 // Para el ordenamiento manual de las tareas
)
