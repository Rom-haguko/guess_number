package com.example.guess_number

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etMin = findViewById<EditText>(R.id.etMin)
        val etMax = findViewById<EditText>(R.id.etMax)
        val btnStart = findViewById<Button>(R.id.btnStart)

        btnStart.setOnClickListener {
            val minStr = etMin.text.toString()
            val maxStr = etMax.text.toString()

            if (minStr.isNotEmpty() && maxStr.isNotEmpty()) {
                val min = minStr.toInt()
                val max = maxStr.toInt()

                if (min < max) {
                    val intent = Intent(this, GuessActivity::class.java)
                    intent.putExtra("MIN", min)
                    intent.putExtra("MAX", max)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Минимальное число должно быть меньше максимального", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Заполните оба поля", Toast.LENGTH_SHORT).show()
            }
        }
    }
}