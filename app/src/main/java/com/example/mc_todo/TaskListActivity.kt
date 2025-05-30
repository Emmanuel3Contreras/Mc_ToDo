package com.example.mc_todo // Asegúrate de que este sea el nombre correcto de tu paquete

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class TaskListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Establece el layout para esta actividad.
        // activity_task_list.xml aún no existe, lo crearemos en el siguiente paso.
        setContentView(R.layout.activity_task_list)

        // Aquí es donde en el futuro se inicializará el RecyclerView,
        // se cargarán las tareas, etc.
    }
}
