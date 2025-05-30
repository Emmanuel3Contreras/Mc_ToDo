package com.example.mc_todo // ¡Asegúrate de que este sea el nombre correcto de tu paquete!

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {

    // Define la duración de la pantalla de inicio en milisegundos
    private val SPLASH_TIME_OUT: Long = 3000 // 3 segundos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Establece el diseño de la pantalla de inicio
        setContentView(R.layout.activity_splash_screen) // ¡Apuntamos al nuevo layout!

        // Usa un Handler para retrasar el inicio de la actividad principal
        Handler(Looper.getMainLooper()).postDelayed({
            // Crea un Intent para iniciar la actividad principal de tu aplicación
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent) // Inicia la actividad principal

            finish() // Finaliza la actividad de la pantalla de inicio
        }, SPLASH_TIME_OUT)
    }
}