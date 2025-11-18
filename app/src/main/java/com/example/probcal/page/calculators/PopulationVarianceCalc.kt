package com.example.probcal.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardOptions

@Composable
fun PopulationVarianceCalc(modifier: Modifier = Modifier) {

    var input by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<Double?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Population Variance Calculator",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("Enter numbers separated by commas (e.g., 1, 2, 3)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                val numbers = input
                    .split(",")
                    .mapNotNull { it.trim().toDoubleOrNull() }

                if (numbers.isNotEmpty()) {
                    val mean = numbers.sum() / numbers.size
                    val variance = numbers.sumOf { (it - mean) * (it - mean) } / numbers.size
                    result = variance
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calculate")
        }

        Spacer(modifier = Modifier.height(20.dp))

        result?.let {
            Text(
                text = "Population Variance: ${"%.2f".format(it)}",
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}
