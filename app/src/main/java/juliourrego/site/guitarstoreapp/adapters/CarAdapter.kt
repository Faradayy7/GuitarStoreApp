package juliourrego.site.guitarstoreapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import juliourrego.site.guitarstoreapp.R
import juliourrego.site.guitarstoreapp.models.Guitar

class CartAdapter(
    private val cartList: MutableList<Guitar>,
    private val onRemoveClick: (Guitar) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.guitar_name)
        val priceTextView: TextView = itemView.findViewById(R.id.price_text)
        val imageView: ImageView = itemView.findViewById(R.id.guitar_image)
        val removeButton: Button = itemView.findViewById(R.id.remove_from_cart_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val guitar = cartList[position]
        holder.nameTextView.text = guitar.name
        holder.priceTextView.text = "$${"%.2f".format(guitar.price)}"
        holder.imageView.setImageResource(guitar.imageResId)
        holder.removeButton.setOnClickListener {
            onRemoveClick(guitar)
        }
    }

    override fun getItemCount(): Int = cartList.size
}
