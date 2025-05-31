package juliourrego.site.guitarstoreapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import juliourrego.site.guitarstoreapp.adapters.GuitarAdapter
import juliourrego.site.guitarstoreapp.models.Guitar
import juliourrego.site.guitarstoreapp.utils.CartManager
import juliourrego.site.guitarstoreapp.utils.SessionManager

class CatalogActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GuitarAdapter
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog)

        sessionManager = SessionManager(this)

        recyclerView = findViewById(R.id.catalog_recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Productos mockeados localmente
        val guitars = listOf(
            Guitar("Fender Stratocaster", 1200.0, R.drawable.guitar_1),
            Guitar("Gibson Les Paul", 2500.0, R.drawable.guitar_2),
            Guitar("Ibanez RG", 900.0, R.drawable.guitar_3),
            Guitar("Yamaha Pacifica", 400.0, R.drawable.guitar_4),
            Guitar("PRS Custom 24", 3500.0, R.drawable.guitar_5)
        )

        adapter = GuitarAdapter(guitars) { guitar ->
            CartManager.addToCart(guitar)
        }
        recyclerView.adapter = adapter

        // Botón para abrir carrito
        val cartFab = findViewById<FloatingActionButton>(R.id.cart_fab)
        cartFab.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

        // Botón para cerrar sesión
        val logoutFab = findViewById<ExtendedFloatingActionButton>(R.id.logout_button)
        logoutFab.setOnClickListener {
            sessionManager.logout()
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        // Botón para ir al perfil
        val btnEditProfile = findViewById<Button>(R.id.btnEditProfile)
        btnEditProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }
}

