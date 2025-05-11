package juliourrego.site.guitarstoreapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import juliourrego.site.guitarstoreapp.utils.SessionManager

class LoginActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Instanciar SessionManager
        sessionManager = SessionManager(this)

        // Si ya hay sesión, ir directo al catálogo
        if (sessionManager.isLoggedIn()) {
            navigateToCatalog()
        }

        val emailInput = findViewById<EditText>(R.id.email_input)
        val passwordInput = findViewById<EditText>(R.id.password_input)
        val loginButton = findViewById<Button>(R.id.login_button)
        val createAccountLink = findViewById<TextView>(R.id.create_account)

        loginButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString()

            // Validación simulada
            if (email == "admin@guitar.com" && password == "1234") {
                sessionManager.saveUser(email, "admin")
                navigateToCatalog()
            } else if (email == "cliente@guitar.com" && password == "1234") {
                sessionManager.saveUser(email, "cliente")
                navigateToCatalog()
            } else {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }

        createAccountLink.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun navigateToCatalog() {
        val intent = Intent(this, CatalogActivity::class.java)
        startActivity(intent)
        finish()
    }
}
