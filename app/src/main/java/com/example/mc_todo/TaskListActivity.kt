package com.example.mc_todo // Asegúrate de que este sea el nombre correcto de tu paquete

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Date

class TaskListActivity : AppCompatActivity() {

    private lateinit var tasksAdapter: TasksAdapter
    private val tasksList: MutableList<Task> = mutableListOf() // Lista mutable de tareas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)

        // Inicializa el RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.rv_tasks)

        // Configura el LayoutManager (cómo se organizan los elementos: lineal, cuadrícula, etc.)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Inicializa el adaptador con una lista vacía y los callbacks
        tasksAdapter = TasksAdapter(
            tasksList,
            onTaskCheckedChangeListener = { task, isChecked ->
                // Lógica cuando el checkbox de una tarea cambia de estado
                Log.d("TaskListActivity", "Tarea '${task.title}' completada: $isChecked")
                updateTaskCompletionStatus(task, isChecked)
            },
            onTaskClickListener = { task ->
                // Lógica cuando se hace clic en una tarea (para editarla, por ejemplo)
                Log.d("TaskListActivity", "Clic en tarea: ${task.title}")
                // Aquí podrías abrir una pantalla de edición
            }
        )
        recyclerView.adapter = tasksAdapter

        // Añade algunas tareas de prueba
        addSampleTasks()
    }

    /**
     * Añade algunas tareas de ejemplo a la lista.
     */
    private fun addSampleTasks() {
        tasksList.add(Task(title = "Comprar víveres", dueDate = Date()))
        tasksList.add(Task(title = "Terminar informe de proyecto", dueDate = Date(System.currentTimeMillis() + 86400000))) // Mañana
        tasksList.add(Task(title = "Llamar a Juan", isCompleted = true)) // Tarea completada de ejemplo
        tasksList.add(Task(title = "Planificar viaje"))
        tasksList.add(Task(title = "Hacer ejercicio"))
        tasksList.add(Task(title = "Leer un libro", dueDate = Date(System.currentTimeMillis() + 2 * 86400000))) // Pasado mañana

        // Notifica al adaptador que los datos han cambiado para que el RecyclerView se actualice
        tasksAdapter.notifyDataSetChanged()
        // Después de añadir, reordenamos para que las completadas estén al final
        reorderTasksByCompletionStatus()
    }

    /**
     * Actualiza el estado de completado de una tarea y reordena la lista.
     * @param task La tarea cuyo estado ha cambiado.
     * @param isChecked El nuevo estado de completado.
     */
    private fun updateTaskCompletionStatus(task: Task, isChecked: Boolean) {
        val index = tasksList.indexOf(task)
        if (index != -1) {
            tasksList[index].isCompleted = isChecked
            // Reordenar la lista para mover las completadas al final
            reorderTasksByCompletionStatus()
        }
    }

    /**
     * Reordena la lista de tareas para que las completadas estén al final.
     * Mantiene el orden relativo dentro de cada grupo (pendientes y completadas).
     */
    private fun reorderTasksByCompletionStatus() {
        val pendingTasks = tasksList.filter { !it.isCompleted }.toMutableList()
        val completedTasks = tasksList.filter { it.isCompleted }.toMutableList()

        // Limpia la lista original y añade primero las pendientes, luego las completadas
        tasksList.clear()
        tasksList.addAll(pendingTasks)
        tasksList.addAll(completedTasks)

        tasksAdapter.notifyDataSetChanged() // Una notificación completa es más sencilla para reordenamientos complejos
    }
}