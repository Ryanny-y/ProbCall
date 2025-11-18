package com.example.probcal.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun SampleVariancePage(modifier: Modifier = Modifier) {

    var input by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<Double?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sample Variance Calculator",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = input,
            onValueChange = {
                input = it
                error = null
            },
            label = { Text("Enter numbers separated by commas (e.g., 1, 2, 3)") },
            modifier = Modifier.fillMaxWidth(),
            isError = error != null,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        if (error != null) {
            Text(error!!, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                val numbers = input
                    .split(",")
                    .mapNotNull { it.trim().toDoubleOrNull() }

                if (numbers.size < 2) {
                    error = "Sample variance requires at least 2 numbers."
                    result = null
                    return@Button
                }

                val mean = numbers.sum() / numbers.size
                val squaredDiffs = numbers.map { (it - mean) * (it - mean) }
                val variance = squaredDiffs.sum() / (numbers.size - 1)

                result = variance
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calculate")
        }

        Spacer(modifier = Modifier.height(20.dp))

        result?.let {
            Text(
                text = "Sample Variance: ${"%.2f".format(it)}",
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}
