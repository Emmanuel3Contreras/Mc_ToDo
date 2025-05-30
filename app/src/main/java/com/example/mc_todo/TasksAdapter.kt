package com.example.mc_todo // Asegúrate de que este sea el nombre correcto de tu paquete

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Adaptador para el RecyclerView que muestra la lista de tareas.
 * @param tasks La lista mutable de tareas a mostrar.
 * @param onTaskCheckedChangeListener Listener para cuando el estado de completado de una tarea cambia.
 * @param onTaskClickListener Listener para cuando se hace clic en una tarea (para edición, etc.).
 */
class TasksAdapter(
    private val tasks: MutableList<Task>,
    private val onTaskCheckedChangeListener: (Task, Boolean) -> Unit, // Callback para el checkbox
    private val onTaskClickListener: (Task) -> Unit // Callback para hacer clic en la tarjeta
) : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {

    // Formato para mostrar la fecha de vencimiento
    private val dateFormatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    /**
     * ViewHolder que contiene y gestiona las vistas de un solo elemento de tarea (item_task.xml).
     */
    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkBox: CheckBox = itemView.findViewById(R.id.cb_task_completed)
        val titleTextView: TextView = itemView.findViewById(R.id.tv_task_title)
        val dueDateTextView: TextView = itemView.findViewById(R.id.tv_task_due_date)

        // Inicializa los listeners una vez por ViewHolder
        init {
            // Listener para el checkbox
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                // Llama al callback proporcionado por la actividad/fragmento
                onTaskCheckedChangeListener.invoke(tasks[adapterPosition], isChecked)
            }

            // Listener para hacer clic en toda la tarjeta (excluyendo el checkbox)
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onTaskClickListener.invoke(tasks[adapterPosition])
                }
            }
        }

        /**
         * Bindea los datos de una tarea a las vistas del ViewHolder.
         * @param task La tarea a mostrar.
         */
        fun bind(task: Task) {
            // Evita que el listener se dispare al binear
            checkBox.setOnCheckedChangeListener(null) // Desactiva temporalmente el listener
            checkBox.isChecked = task.isCompleted
            checkBox.setOnCheckedChangeListener { _, isChecked -> // Vuelve a activar el listener
                onTaskCheckedChangeListener.invoke(tasks[adapterPosition], isChecked)
            }

            titleTextView.text = task.title

            // Muestra la fecha de vencimiento si existe, de lo contrario la oculta
            task.dueDate?.let {
                dueDateTextView.text = dateFormatter.format(it)
                dueDateTextView.visibility = View.VISIBLE
            } ?: run {
                dueDateTextView.visibility = View.GONE
            }

            // Aplica la transparencia si la tarea está completada
            itemView.alpha = if (task.isCompleted) 0.5f else 1.0f // 0.5f para 50% de transparencia
        }
    }

    /**
     * Crea y devuelve un nuevo ViewHolder cuando el RecyclerView necesita uno.
     * Infla el layout item_task.xml.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    /**
     * Devuelve el número total de elementos en la lista de datos.
     */
    override fun getItemCount(): Int = tasks.size

    /**
     * Bindea los datos a un ViewHolder existente.
     * Se llama para mostrar los datos en una posición específica.
     */
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    /**
     * Actualiza la lista de tareas y notifica al adaptador sobre los cambios.
     * @param newTasks La nueva lista de tareas.
     */
    fun updateTasks(newTasks: List<Task>) {
        tasks.clear()
        tasks.addAll(newTasks)
        notifyDataSetChanged() // Notifica que los datos han cambiado (simple, pero no el más eficiente para grandes listas)
    }

    /**
     * Mueve una tarea a una nueva posición en la lista.
     * Usado para el drag-and-drop.
     * @param fromPosition Posición inicial de la tarea.
     * @param toPosition Posición final de la tarea.
     */
    fun moveItem(fromPosition: Int, toPosition: Int) {
        val movedTask = tasks.removeAt(fromPosition)
        tasks.add(toPosition, movedTask)
        notifyItemMoved(fromPosition, toPosition)
    }

    /**
     * Elimina una tarea de la lista.
     * @param position La posición de la tarea a eliminar.
     */
    fun removeItem(position: Int) {
        tasks.removeAt(position)
        notifyItemRemoved(position)
    }
}