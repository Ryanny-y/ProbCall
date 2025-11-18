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
fun PopulationVarianceCalc(modifier: Modifier = Modifier) {
    var input by remember { mutableStateOf("") }
    var varianceResult by remember { mutableStateOf<Double?>(null) }
    var sdResult by remember { mutableStateOf<Double?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

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
            onValueChange = {
                input = it
                error = null
            },
            label = { Text("Enter numbers separated by commas") },
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

                if (numbers.isEmpty()) {
                    error = "Please enter valid numbers."
                    varianceResult = null
                    sdResult = null
                    return@Button
                }

                val mean = numbers.sum() / numbers.size
                val variance = numbers.sumOf { (it - mean) * (it - mean) } / numbers.size
                varianceResult = variance
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calculate Population Variance")
        }

        Spacer(modifier = Modifier.height(20.dp))

        varianceResult?.let {
            Text(
                text = "Population Variance: ${"%.2f".format(it)}",
                style = MaterialTheme.typography.headlineSmall
            )
        }

        if(varianceResult != null) {
            Spacer(modifier = Modifier.height(20.dp))
        }

        Button(
            onClick = {
                val numbers = input
                    .split(",")
                    .mapNotNull { it.trim().toDoubleOrNull() }

                if (numbers.isEmpty()) {
                    error = "Please enter valid numbers."
                    varianceResult = null
                    sdResult = null
                    return@Button
                }

                val mean = numbers.sum() / numbers.size
                val variance = numbers.sumOf { (it - mean) * (it - mean) } / numbers.size
                sdResult = sqrt(variance)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calculate Standard Deviation")
        }

        Spacer(modifier = Modifier.height(20.dp))

        sdResult?.let {
            Text(
                text = "Standard Deviation: ${"%.2f".format(it)}",
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}
