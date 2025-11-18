package com.example.probcal.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlin.math.sqrt

@Composable
fun ZScoreCalculatorCalc(modifier: Modifier = Modifier) {

    var numbersInput by remember { mutableStateOf("") }
    var targetInput by remember { mutableStateOf("") }
    var zScoreResult by remember { mutableStateOf<Double?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Z-Score Calculator",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = numbersInput,
            onValueChange = {
                numbersInput = it
                error = null
            },
            label = { Text("Enter numbers separated by commas") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = targetInput,
            onValueChange = {
                targetInput = it
                error = null
            },
            label = { Text("Enter value to calculate Z-score") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        if (error != null) {
            Text(error!!, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                val numbers = numbersInput
                    .split(",")
                    .mapNotNull { it.trim().toDoubleOrNull() }

                val x = targetInput.toDoubleOrNull()

                if (numbers.isEmpty() || x == null) {
                    error = "Please enter valid numbers and a target value."
                    zScoreResult = null
                    return@Button
                }

                val mean = numbers.sum() / numbers.size
                val variance = numbers.sumOf { (it - mean) * (it - mean) } / numbers.size
                val sd = sqrt(variance)

                if (sd == 0.0) {
                    error = "Standard deviation is 0, cannot calculate Z-score."
                    zScoreResult = null
                    return@Button
                }

                zScoreResult = (x - mean) / sd
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calculate Z-Score")
        }

        Spacer(modifier = Modifier.height(20.dp))

        zScoreResult?.let {
            Text(
                text = "Z-Score: ${"%.2f".format(it)}",
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}
