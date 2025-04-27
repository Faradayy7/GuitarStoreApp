package juliourrego.site.guitarstoreapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailInput = findViewById<EditText>(R.id.email_input)
        val passwordInput = findViewById<EditText>(R.id.password_input)
        val loginButton = findViewById<Button>(R.id.login_button)
        val createAccountLink = findViewById<TextView>(R.id.create_account)

        loginButton.setOnClickListener {
            // Aquí en el futuro validarás email y password si quieres.
            // Por ahora, simplemente ir al catálogo.
            val intent = Intent(this, CatalogActivity::class.java)
            startActivity(intent)
        }

        createAccountLink.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}