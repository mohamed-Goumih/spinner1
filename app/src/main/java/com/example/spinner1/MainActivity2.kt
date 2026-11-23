package com.example.spinner1



import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {

    // Liste mutable : on pourra ajouter et supprimer
    private val fruits = mutableListOf("Pomme", "Banane", "Orange", "Fraise", "Mangue")
    private lateinit var fruitsAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main2)


        val fruitsListView: ListView = findViewById(R.id.fruits_list_view)
        val etFruit = findViewById<EditText>(R.id.etFruit)

        fruitsAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, fruits)
        fruitsListView.adapter = fruitsAdapter

        // Clic : un message
        fruitsListView.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val selectedFruit = fruits[position]
                Toast.makeText(
                    this, "Vous avez sélectionné : $selectedFruit", Toast.LENGTH_SHORT
                ).show()
            }

        // Appui long : suppression
        fruitsListView.onItemLongClickListener =
            AdapterView.OnItemLongClickListener { _, _, position, _ ->
                removeFruit(position)
                true
            }

        findViewById<Button>(R.id.btnAjouter).setOnClickListener {
            val nom = etFruit.text.toString().trim()
            if (nom.isNotEmpty()) {
                addFruit(nom)
                etFruit.text.clear()
            }
        }
    }

    private fun addFruit(newFruit: String) {
        fruits.add(newFruit)
        fruitsAdapter.notifyDataSetChanged()
    }

    private fun removeFruit(position: Int) {
        if (position in fruits.indices) {
            fruits.removeAt(position)
            fruitsAdapter.notifyDataSetChanged()
        }
    }
}
