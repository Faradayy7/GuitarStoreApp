package juliourrego.site.guitarstoreapp

import android.content.Intent
import android.os.Bundle
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

        val guitars = listOf(
            Guitar("Fender Stratocaster", "$1200", R.drawable.guitar_1),
            Guitar("Gibson Les Paul", "$2500", R.drawable.guitar_2),
            Guitar("Ibanez RG", "$900", R.drawable.guitar_3),
            Guitar("Yamaha Pacifica", "$400", R.drawable.guitar_4),
            Guitar("PRS Custom 24", "$3500", R.drawable.guitar_5)
        )

        adapter = GuitarAdapter(guitars) { guitar ->
            CartManager.addToCart(guitar)
        }
        recyclerView.adapter = adapter

        val cartFab = findViewById<FloatingActionButton>(R.id.cart_fab)
        cartFab.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        val logoutFab = findViewById<ExtendedFloatingActionButton>(R.id.logout_button)
        logoutFab.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            sessionManager.logout()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}
