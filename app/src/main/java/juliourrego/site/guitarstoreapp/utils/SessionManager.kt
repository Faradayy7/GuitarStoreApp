package juliourrego.site.guitarstoreapp.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class SessionManager(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveUser(username: String, role: String) {
        prefs.edit().apply {
            putString(KEY_USERNAME, username)
            putString(KEY_ROLE, role)
            putBoolean(KEY_LOGGED_IN, true)
            apply()
        }
    }

    fun getUsername(): String? = prefs.getString(KEY_USERNAME, null)

    fun getRole(): String? = prefs.getString(KEY_ROLE, null)

    fun isLoggedIn(): Boolean = prefs.getBoolean(KEY_LOGGED_IN, false)

    fun logout() {
        prefs.edit { clear() }
    }

    companion object {
        private const val PREFS_NAME = "user_session"
        private const val KEY_USERNAME = "USERNAME"
        private const val KEY_ROLE = "ROLE"
        private const val KEY_LOGGED_IN = "IS_LOGGED_IN"
    }
}
