package com.example.probcal.page.calculators

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.probcal.ui.component.CalculatorScreen
import org.apache.commons.math3.special.Erf
import kotlin.math.sqrt

@Composable
fun ZScoreAreaCalc(modifier: Modifier = Modifier) {

    var zInput by remember { mutableStateOf("") }
    var areaResult by remember { mutableStateOf<Double?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    CalculatorScreen(
        modifier = modifier,
        title = "Z-Score Area Calculator",
        description = "Calculate the area (probability) under the standard normal curve for a given Z-score."
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = zInput,
                    onValueChange = {
                        zInput = it
                        error = null
                    },
                    label = { Text("Enter Z-Score") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(keyboardType = KeyboardType.Number)
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
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Calculate Area")
                }
            }
        }


        Spacer(modifier = Modifier.height(20.dp))

        areaResult?.let { area ->
            // Answer card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Area (Probability)", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "%.4f".format(area),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${"%.2f".format(area * 100)}%",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Cumulative Probability: The probability that a value is less than or equal to z = $zInput is ${"%.4f".format(area)} or ${"%.2f".format(area * 100)}%.\n\n" +
                                "Right Tail: P(Z > $zInput) = ${"%.4f".format(1 - area)} or ${"%.2f".format((1 - area) * 100)}%",
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Interpretation card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text("Interpretation", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "The area under the standard normal curve from -∞ to z = $zInput is ${"%.4f".format(area)}.\n\n" +
                                "This represents the cumulative probability in the standard normal distribution (mean = 0, standard deviation = 1).\n\n" +
                                "Common Z-Values:\n" +
                                "z = 1.645 → Area = 0.9500 (95th percentile)\n" +
                                "z = 1.960 → Area = 0.9750 (97.5th percentile)\n" +
                                "z = 2.326 → Area = 0.9900 (99th percentile)\n" +
                                "z = 2.576 → Area = 0.9950 (99.5th percentile)",
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}
