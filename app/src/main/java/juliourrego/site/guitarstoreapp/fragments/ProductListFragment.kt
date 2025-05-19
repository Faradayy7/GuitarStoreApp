package juliourrego.site.guitarstoreapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import juliourrego.site.guitarstoreapp.models.Guitar
import juliourrego.site.guitarstoreapp.adapters.GuitarAdapter

import juliourrego.site.guitarstoreapp.R

class ProductListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GuitarAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_list, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val guitars = listOf(
            Guitar("Fender Stratocaster", 1200.0, R.drawable.guitar_1),
            Guitar("Gibson Les Paul", 2500.0, R.drawable.guitar_2),
            Guitar("Ibanez RG", 900.0, R.drawable.guitar_3)
        )

        adapter = GuitarAdapter(guitars) { guitar ->
            Toast.makeText(requireContext(), "${guitar.name} añadido al carrito", Toast.LENGTH_SHORT).show()
        }

        recyclerView.adapter = adapter
        return view
    }
}
