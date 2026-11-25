package com.example.spinner1

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.abs

class ListActivity : AppCompatActivity() {

    private lateinit var fruitsListView: ListView
    private lateinit var fruitsAdapter: ArrayAdapter<String>
    private lateinit var btnAdd: Button
    private lateinit var etFruit: EditText

    private val fruits = mutableListOf("Pomme", "Banane", "Orange", "Fraise", "Mange")

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        // Inflate views
        fruitsListView = findViewById(R.id.fruits_list_view)
        fruitsAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, fruits)
        btnAdd = findViewById(R.id.btnAdd)
        etFruit = findViewById(R.id.etFruit)

        // Set adapter
        fruitsListView.adapter = fruitsAdapter

        fruitsListView.setOnItemClickListener { _, _, position, _ ->
            val selectedFruit = fruits[position]
            Toast.makeText(this, selectedFruit, Toast.LENGTH_LONG).show()
        }

        fruitsListView.setOnItemLongClickListener { _, _, position, _ ->
            //showDeleteConfirmationDialog(position)
           // true
            removeFruit(position)
            true
        }

        // Swipe gesture detector for swipe-to-delete
//        val gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
//            override fun onFling(
//                e1: MotionEvent?,
//                e2: MotionEvent,
//                velocityX: Float,
//                velocityY: Float
//            ): Boolean {
//                if (e1 != null) {
//                    val diffX = e2.x - e1.x
//                    if (abs(diffX) > 100 && abs(velocityX) > 100) {
//                        val position = fruitsListView.pointToPosition(e1.x.toInt(), e1.y.toInt())
//                        if (position != AdapterView.INVALID_POSITION) {
//                            showDeleteConfirmationDialog(position)
//                            return true
//                        }
//                    }
//                }
//                return false
//            }
//        })

//        fruitsListView.setOnTouchListener { view, event ->
//            gestureDetector.onTouchEvent(event)
//            view.performClick()
//            false
//        }

        btnAdd.setOnClickListener {
            val fruit = etFruit.text.toString().trim()
            if (fruit.isNotEmpty()) {
                addFruit(fruit)
                etFruit.text.clear()
            }
        }
    }

    // Function to add a fruit
    fun addFruit(fruit: String) {
        fruits.add(fruit)
        fruitsAdapter.notifyDataSetChanged()
    }

    // Function to remove a fruit
    fun removeFruit(position: Int) {
        if (position in fruits.indices) {
            val fruitName = fruits[position]
            AlertDialog.Builder(this)
                .setTitle(R.string.delete_confirmation_title)
                .setMessage(getString(R.string.delete_confirmation_message, fruitName))
                .setPositiveButton(R.string.delete) { _, _ ->
                    fruits.removeAt(position)
                    Toast.makeText(this, getString(R.string.fruit_deleted, fruitName), Toast.LENGTH_SHORT).show()
                    fruitsAdapter.notifyDataSetChanged()
                }
                .setNegativeButton(R.string.cancel, null)
                .show()
        }
    }

    // Function to show delete confirmation dialog
//    fun showDeleteConfirmationDialog(position: Int) {
//        if (position !in fruits.indices) return
//        val fruitName = fruits[position]
//        AlertDialog.Builder(this)
//            .setTitle(R.string.delete_confirmation_title)
//            .setMessage(getString(R.string.delete_confirmation_message, fruitName))
//            .setPositiveButton(R.string.delete) { _, _ ->
//                removeFruit(position)
//            }
//            .setNegativeButton(R.string.cancel, null)
//            .show()
//    }

}

