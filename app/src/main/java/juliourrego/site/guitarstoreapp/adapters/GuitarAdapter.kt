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

class GuitarAdapter(
    private val guitarList: List<Guitar>,
    private val onAddToCartClick: (Guitar) -> Unit
) : RecyclerView.Adapter<GuitarAdapter.GuitarViewHolder>() {

    inner class GuitarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val guitarImage: ImageView = itemView.findViewById(R.id.guitar_image)
        val guitarName: TextView = itemView.findViewById(R.id.guitar_name)
        val guitarPrice: TextView = itemView.findViewById(R.id.guitar_price)
        val addToCartButton: Button = itemView.findViewById(R.id.add_to_cart_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuitarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_guitar, parent, false)
        return GuitarViewHolder(view)
    }

    override fun onBindViewHolder(holder: GuitarViewHolder, position: Int) {
        val guitar = guitarList[position]
        holder.guitarImage.setImageResource(guitar.imageResId)
        holder.guitarName.text = guitar.name
        holder.guitarPrice.text = guitar.price
        holder.addToCartButton.setOnClickListener {
            onAddToCartClick(guitar)
        }
    }

    override fun getItemCount(): Int = guitarList.size
}

