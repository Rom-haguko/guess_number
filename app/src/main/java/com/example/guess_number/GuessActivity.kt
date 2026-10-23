package com.example.guess_number

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class GuessActivity : AppCompatActivity() {

    private var min: Int = 0
    private var max: Int = 0
    private var guess: Int = 0

    private lateinit var tvGuess: TextView
    private lateinit var btnLess: Button
    private lateinit var btnGreater: Button
    private lateinit var btnCorrect: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guess)

        min = intent.getIntExtra("MIN", 1)
        max = intent.getIntExtra("MAX", 100)

        tvGuess = findViewById(R.id.tvGuess)
        btnLess = findViewById(R.id.btnLess)
        btnGreater = findViewById(R.id.btnGreater)
        btnCorrect = findViewById(R.id.btnCorrect)

        makeGuess()

        btnLess.setOnClickListener {
            max = guess - 1
            checkRangeAndGuess()
        }

        btnGreater.setOnClickListener {
            min = guess + 1
            checkRangeAndGuess()
        }

        btnCorrect.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Победа!")
                .setMessage("Я угадал ваше число: $guess")
                .setPositiveButton("OK") { _, _ -> finish() }
                .show()
        }
    }

    private fun makeGuess() {
        guess = (min + max) / 2
        tvGuess.text = "Ваше число: $guess?"
    }

    private fun checkRangeAndGuess() {
        if (min > max) {
            AlertDialog.Builder(this)
                .setTitle("Ошибка")
                .setMessage("Вы дали противоречивые подсказки. Начните сначала.")
                .setPositiveButton("OK") { _, _ -> finish() }
                .show()
        } else {
            makeGuess()
            if (min == max) {
                btnLess.isEnabled = false
                btnGreater.isEnabled = false
            }
        }
    }
}