package juliourrego.site.guitarstoreapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        // Configura el título y el botón de retroceso en la ActionBar
        supportActionBar?.title = "Panel Administrador"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // Habilita que el botón atrás de la barra funcione
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
