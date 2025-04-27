package juliourrego.site.guitarstoreapp

object CartManager {
    private val cartItems = mutableListOf<Guitar>()

    fun addToCart(guitar: Guitar) {
        cartItems.add(guitar)
    }

    fun getCartItems(): List<Guitar> = cartItems

    fun getTotalPrice(): Double {
        return cartItems.sumOf { it.price.replace("$", "").toDouble() }
    }

    fun clearCart() {
        cartItems.clear()
    }
}