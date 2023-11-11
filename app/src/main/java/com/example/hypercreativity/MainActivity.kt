package com.example.hypercreativity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var randomTermTextView: TextView
    private lateinit var randomizeButton: Button
    private var terms: List<String> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        randomTermTextView = findViewById(R.id.random_term_text_view)
        randomizeButton = findViewById(R.id.randomize_button)

        // Load the terms from the CSV when the app starts
        loadTermsFromCSV("ideas.csv")

        randomizeButton.setOnClickListener {
            showRandomTerm()
        }
    }

    private fun loadTermsFromCSV(filename: String) {
        val inputStream = assets.open(filename)
        val buffer = BufferedReader(InputStreamReader(inputStream))
        val lines = mutableListOf<String>()

        buffer.forEachLine {
            lines.add(it)
        }

        terms = lines
        buffer.close()
    }

    private fun showRandomTerm() {
        if (terms.isNotEmpty()) {
            val randomTerm = terms[Random().nextInt(terms.size)]
            randomTermTextView.text = randomTerm
        }
    }
}
