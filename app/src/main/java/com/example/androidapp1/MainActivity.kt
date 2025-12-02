package com.example.androidapp1

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * MainActivity - simple Tap Counter app
 *
 * Functionality:
 * - Shows the current count in a TextView
 * - 'Tap' button increments the count
 * - 'Reset' button sets count to zero
 * - Count is persisted across app restarts using SharedPreferences
 */
class MainActivity : AppCompatActivity() {

    private var count = 0
    private val PREFS_NAME = "tap_prefs"
    private val KEY_COUNT = "count"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Restore count from SharedPreferences
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        count = prefs.getInt(KEY_COUNT, 0)

        val countText = findViewById<TextView>(R.id.countText)
        val incrementButton = findViewById<Button>(R.id.incrementButton)
        val resetButton = findViewById<Button>(R.id.resetButton)

        // Initialize UI
        countText.text = count.toString()

        // Tap: increment count and persist
        incrementButton.setOnClickListener {
            count++
            countText.text = count.toString()
            prefs.edit().putInt(KEY_COUNT, count).apply()
        }

        // Reset: zero the count and persist
        resetButton.setOnClickListener {
            count = 0
            countText.text = "0"
            prefs.edit().putInt(KEY_COUNT, count).apply()
        }
    }
}
