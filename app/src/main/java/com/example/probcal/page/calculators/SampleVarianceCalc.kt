package com.example.probcal.page.calculators

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.probcal.ui.component.CalculatorScreen
import com.example.probcal.ui.component.StepCard
import kotlin.math.pow
import kotlin.math.sqrt

@Composable
fun SampleVarianceCalc(modifier: Modifier = Modifier) {

    var input by remember { mutableStateOf("") }
    var varianceResult by remember { mutableStateOf<Double?>(null) }
    var sdResult by remember { mutableStateOf<Double?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    val numbers = input
        .split(",")
        .mapNotNull { it.trim().toDoubleOrNull() }

    CalculatorScreen(
        modifier = modifier,
        title = "Sample Variance Calculator",
        description = "Calculate the sample variance (s²) and sample standard deviation (s)."
    ) {
        // Input Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Enter numbers separated by commas", fontSize = 12.sp)

                Spacer(Modifier.height(6.dp))

                OutlinedTextField(
                    value = input,
                    onValueChange = {
                        input = it
                        error = null
                    },
                    label = { Text("(e.g., 4, 6, 8, 10)", fontSize = 12.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    isError = error != null
                )

                error?.let {
                    Spacer(Modifier.height(6.dp))
                    Text(text = it, color = MaterialTheme.colorScheme.error)
                }

                Spacer(Modifier.height(12.dp))

                Button(
                    onClick = {
                        if (numbers.size < 2) {
                            error = "Sample variance requires at least 2 numbers."
                            varianceResult = null
                            sdResult = null
                        } else {
                            val mean = numbers.sum() / numbers.size
                            varianceResult = numbers.sumOf { (it - mean).pow(2) } / (numbers.size - 1)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Calculate Sample Variance")
                }

                Spacer(Modifier.height(8.dp))

                Button(
                    onClick = {
                        if (numbers.size < 2) {
                            error = "Sample variance requires at least 2 numbers."
                            varianceResult = null
                            sdResult = null
                        } else {
                            val mean = numbers.sum() / numbers.size
                            val variance = numbers.sumOf { (it - mean).pow(2) } / (numbers.size - 1)
                            sdResult = sqrt(variance)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Calculate Standard Deviation")
                }
            }
        }

        Spacer(Modifier.height(20.dp))

        // Sample Variance Result & Steps
        varianceResult?.let { variance ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Sample Variance Result", style = MaterialTheme.typography.titleSmall)

                    Text(
                        text = "%.4f".format(variance),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        buildAnnotatedString {
                            append("The sample variance of the given dataset is ")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("%.4f".format(variance))
                            }
                        },
                        fontSize = 14.sp
                    )

                    Spacer(Modifier.height(12.dp))

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 300.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val mean = numbers.sum() / numbers.size
                        val deviations = numbers.map { it - mean }
                        val squaredDevs = deviations.map { it.pow(2) }

                        item {
                            Text("Step-By-Step Solution", style = MaterialTheme.typography.titleMedium)
                        }
                        item { StepCard("Step 1: List all numbers", "Numbers: $input") }
                        item { StepCard("Step 2: Count the numbers", "Sample size (n) = ${numbers.size}") }
                        item { StepCard("Step 3: Calculate the mean (x̄)", "x̄ = (${numbers.joinToString(" + ")}) ÷ ${numbers.size} = ${"%.4f".format(mean)}") }
                        item {
                            StepCard(
                                "Step 4: Calculate deviations from mean (x - x̄)",
                                deviations.mapIndexed { index, dev -> "${numbers[index]} - ${"%.4f".format(mean)} = ${"%.4f".format(dev)}" }
                                    .joinToString("\n")
                            )
                        }
                        item {
                            StepCard(
                                "Step 5: Square each deviation (x - x̄)²",
                                squaredDevs.mapIndexed { index, sq -> "(${deviations[index].let { "%.4f".format(it) }})² = ${"%.4f".format(sq)}" }
                                    .joinToString("\n")
                            )
                        }
                        item {
                            StepCard(
                                "Step 6: Sum all squared deviations",
                                "Σ(x - x̄)² = ${squaredDevs.joinToString(" + ") { "%.4f".format(it) }} = ${"%.4f".format(squaredDevs.sum())}"
                            )
                        }
                        item {
                            StepCard(
                                "Step 7: Divide by (n - 1)",
                                "s² = ${"%.4f".format(squaredDevs.sum())} ÷ (${numbers.size} - 1) = ${"%.4f".format(variance)}"
                            )
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(20.dp))

        // Standard Deviation Result & Steps
        sdResult?.let { sd ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Standard Deviation Result", style = MaterialTheme.typography.titleSmall)

                    Text(
                        text = "%.4f".format(sd),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        buildAnnotatedString {
                            append("The sample standard deviation of the given dataset is ")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("%.4f".format(sd))
                            }
                        },
                        fontSize = 14.sp
                    )

                    Spacer(Modifier.height(12.dp))

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 250.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val mean = numbers.sum() / numbers.size
                        val variance = numbers.sumOf { (it - mean).pow(2) } / (numbers.size - 1)

                        item {
                            Text("Step-By-Step Solution", style = MaterialTheme.typography.titleMedium)
                        }
                        item { StepCard("Step 1: Use sample variance (s²)", "s² = ${"%.4f".format(variance)}") }
                        item { StepCard("Step 2: Take square root", "√(${"%.4f".format(variance)}) = ${"%.4f".format(sd)}") }
                        item { StepCard("Step 3: Final standard deviation", "s = ${"%.4f".format(sd)}") }
                    }
                }
            }
        }
    }
}
