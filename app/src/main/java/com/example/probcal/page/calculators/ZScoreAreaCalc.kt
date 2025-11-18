package com.example.probcal.page.calculators

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.apache.commons.math3.special.Erf
import kotlin.math.sqrt

@Composable
fun ZScoreAreaCalc(modifier: Modifier = Modifier) {

    var zInput by remember { mutableStateOf("") }
    var areaResult by remember { mutableStateOf<Double?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Z-Score Area Calculator",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = zInput,
            onValueChange = {
                zInput = it
                error = null
            },
            label = { Text("Enter Z-Score") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        if (error != null) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = error!!, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                val z = zInput.toDoubleOrNull()
                if (z == null) {
                    error = "Please enter a valid number."
                    areaResult = null
                    return@Button
                }

                // Calculate the cumulative area
                areaResult = 0.5 * (1 + Erf.erf(z / sqrt(2.0)))
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calculate Area")
        }

        Spacer(modifier = Modifier.height(20.dp))

        areaResult?.let {
            Text(
                text = "Area under curve (P(Z ≤ ${zInput})): ${"%.4f".format(it)}",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
