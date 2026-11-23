package com.example.spinner1



import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val tvChoix = findViewById<TextView>(R.id.tvChoix)
        val button=findViewById<Button>(R.id.button)

        // ----- Méthode 1 : les éléments viennent de android:entries -----
        val spinner: Spinner = findViewById(R.id.spinner)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener
        {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
            {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                tvChoix.text = selectedItem
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Ne rien faire
            }
        }

        // ----- Méthode 2 : les éléments viennent d'un adaptateur -----
        val spinner2: Spinner = findViewById(R.id.spinner2)
        val itemList = listOf("Element 1", "Element 2", "Element 3", "Element 4")

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, itemList)
        //arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = arrayAdapter

        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                val selectedItem = itemList[position]
                Toast.makeText(
                    this@MainActivity,
                    "Vous avez sélectionné : $selectedItem",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) = Unit


        }

        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                Toast.makeText(this@MainActivity, "Cliqué !", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
