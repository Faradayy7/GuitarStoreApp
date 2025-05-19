package juliourrego.site.guitarstoreapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import juliourrego.site.guitarstoreapp.fragments.ProductListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Carga el fragmento principal solo si no está ya cargado
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProductListFragment())
                .commit()
        }
    }
}
