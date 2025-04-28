package juliourrego.site.guitarstoreapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GuitarAdapter
    private lateinit var totalTextView: TextView
    private lateinit var checkoutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        recyclerView = findViewById(R.id.cart_recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)

        totalTextView = TextView(this)
        checkoutButton = findViewById(R.id.checkout_button)

        val cartItems = CartManager.getCartItems()

        adapter = GuitarAdapter(cartItems) { }
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