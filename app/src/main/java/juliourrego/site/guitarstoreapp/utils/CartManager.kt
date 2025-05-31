package juliourrego.site.guitarstoreapp.utils

import juliourrego.site.guitarstoreapp.models.Guitar

object CartManager {
    private val cart = mutableListOf<Guitar>()

    fun addToCart(guitar: Guitar) {
        cart.add(guitar)
    }

    fun getCartItems(): List<Guitar> = cart.toList()

    fun clearCart() {
        cart.clear()
    }

    fun getTotalPrice(): Double {
        return cart.sumOf { it.price }
    }

    fun removeFromCart(guitar: Guitar) {
        cart.remove(guitar)
    }
}
