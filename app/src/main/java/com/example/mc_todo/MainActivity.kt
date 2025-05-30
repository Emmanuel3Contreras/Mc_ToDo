package com.example.mc_todo // ¡Asegúrate de que este sea el nombre correcto de tu paquete!

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Encuentra el botón por su ID
        val goToTasksButton: Button = findViewById(R.id.btn_go_to_tasks)

        // Configura el Listener de clic para el botón
        goToTasksButton.setOnClickListener {
            // Crea un Intent para iniciar TaskListActivity
            // TaskListActivity aún no existe, la crearemos en el siguiente paso.
            // Por ahora, el IDE puede marcar un error aquí, lo cual es normal.
            val intent = Intent(this, TaskListActivity::class.java)
            startActivity(intent) // Inicia la TaskListActivity
        }
    }
}