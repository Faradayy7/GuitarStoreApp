package juliourrego.site.guitarstoreapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        val emailInput = findViewById<EditText>(R.id.email_input)
        val passwordInput = findViewById<EditText>(R.id.password_input)
        val loginButton = findViewById<Button>(R.id.login_button)
        val createAccount = findViewById<TextView>(R.id.create_account)

        loginButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val userId = auth.currentUser?.uid
                        if (userId != null) {
                            database.child("users").child(userId).child("role")
                                .addListenerForSingleValueEvent(object : ValueEventListener {
                                    override fun onDataChange(snapshot: DataSnapshot) {
                                        val role = snapshot.getValue(String::class.java)
                                        when (role) {
                                            "admin" -> {
                                                startActivity(Intent(this@LoginActivity, AdminActivity::class.java))
                                            }
                                            "cliente" -> {
                                                startActivity(Intent(this@LoginActivity, CatalogActivity::class.java))
                                            }
                                            else -> {
                                                Toast.makeText(this@LoginActivity, "Rol no reconocido", Toast.LENGTH_SHORT).show()
                                            }
                                        }
                                        finish()
                                    }

                                    override fun onCancelled(error: DatabaseError) {
                                        Toast.makeText(this@LoginActivity, "Error al obtener rol", Toast.LENGTH_SHORT).show()
                                    }
                                })
                        }
                    } else {
                        Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        createAccount.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            database.child("users").child(currentUser.uid).child("role")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val role = snapshot.getValue(String::class.java)
                        when (role) {
                            "admin" -> {
                                startActivity(Intent(this@LoginActivity, AdminActivity::class.java))
                            }
                            "cliente" -> {
                                startActivity(Intent(this@LoginActivity, CatalogActivity::class.java))
                            }
                        }
                        finish()
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(this@LoginActivity, "Error al verificar sesión", Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }
}
