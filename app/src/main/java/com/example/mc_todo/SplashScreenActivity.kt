package com.example.mc_todo // ¡Asegúrate de que este sea el nombre correcto de tu paquete!

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {

    // Duración de la pantalla de inicio en milisegundos
    private val SPLASH_TIME_OUT: Long = 3000 // 3 segundos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Usa un Handler para retrasar el inicio de LoginActivity
        Handler(Looper.getMainLooper()).postDelayed({
            // Intent para iniciar LoginActivity (en lugar de MainActivity)
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

            finish() // Cierra la SplashScreen para que no se pueda volver atrás
        }, SPLASH_TIME_OUT)
    }
}