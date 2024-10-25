package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner

class MainActivity : AppCompatActivity() {
    private lateinit var quantityEditText: EditText
    private lateinit var currencySpinner: Spinner

    private val currencyRates = mapOf(
        "Доллар" to 75,
        "Евро" to 90,
        "Тонна" to 100
    )
    private var selectedRate = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        quantityEditText = findViewById(R.id.quantity_edit_text)
        currencySpinner = findViewById(R.id.currency_spinner)
        val calculateButton: Button = findViewById(R.id.calculate_button)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencyRates.keys.toList())
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        currencySpinner.adapter = adapter

        currencySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position) as String
                selectedRate = currencyRates[selectedItem] ?: 0
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                selectedRate = 0
            }
        }
        calculateButton.setOnClickListener {
            val quantityString = quantityEditText.text.toString()
            if (quantityString.isNotEmpty()) {
                val quantity = quantityString.toIntOrNull()
                if (quantity != null) {
                    val totalPrice = selectedRate * quantity
                    val intent = Intent(this, CurrencyActivity::class.java)
                    intent.putExtra("totalPrice", totalPrice)
                    startActivity(intent)
                } else {
                    quantityEditText.error = "Пожалуйста, введите корректное число"
                }
            } else {
                quantityEditText.error = "Введите количество"
            }
        }
    }
}
