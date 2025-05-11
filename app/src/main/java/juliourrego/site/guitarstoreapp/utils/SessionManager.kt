package juliourrego.site.guitarstoreapp.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)

    fun saveUser(username: String, role: String) {
        prefs.edit().apply {
            putString("USERNAME", username)
            putString("ROLE", role)
            putBoolean("IS_LOGGED_IN", true)
            apply()
        }
    }

    fun isLoggedIn(): Boolean = prefs.getBoolean("IS_LOGGED_IN", false)

    fun getUsername(): String? = prefs.getString("USERNAME", null)

    fun getRole(): String? = prefs.getString("ROLE", null)

    fun logout() {
        prefs.edit().clear().apply()
    }
}