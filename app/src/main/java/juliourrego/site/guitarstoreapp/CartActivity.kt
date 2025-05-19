package juliourrego.site.guitarstoreapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import juliourrego.site.guitarstoreapp.adapters.GuitarAdapter
import juliourrego.site.guitarstoreapp.utils.CartManager

class CartActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GuitarAdapter
    private lateinit var checkoutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        recyclerView = findViewById(R.id.cart_recycler)
        checkoutButton = findViewById(R.id.checkout_button)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val cartItems = CartManager.getCartItems()
        adapter = GuitarAdapter(cartItems) { /* No se hace nada al hacer clic en el ítem */ }
        recyclerView.adapter = adapter

        updateTotal()

        checkoutButton.setOnClickListener {
            CartManager.clearCart()
            finish()
        }
    }

    private fun updateTotal() {
        val totalPrice = CartManager.getTotalPrice()
        checkoutButton.text = "Finalizar compra ($${"%.2f".format(totalPrice)})"
    }
}
