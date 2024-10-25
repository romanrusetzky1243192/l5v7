package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CurrencyActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency)

        val Text: TextView = findViewById(R.id.payment_text)
        val totalPrice = intent.getIntExtra("totalPrice", 0)

        Text.text = "Цена = $totalPrice рублей"
    }
}
