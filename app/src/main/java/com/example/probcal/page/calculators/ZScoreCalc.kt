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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.probcal.ui.component.CalculatorScreen
import com.example.probcal.ui.component.StepCard
import kotlin.math.sqrt

@Composable
fun ZScoreCalculatorCalc(modifier: Modifier = Modifier) {

    var numbersInput by remember { mutableStateOf("") }
    var targetInput by remember { mutableStateOf("") }
    var zScoreResult by remember { mutableStateOf<Double?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    val numbers = numbersInput
        .split(",")
        .mapNotNull { it.trim().toDoubleOrNull() }

    val x = targetInput.toDoubleOrNull()

    CalculatorScreen(
        modifier = modifier,
        title = "Z-Score Calculator",
        description = "Calculate the Z-score of a target value in your dataset."
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
                    value = numbersInput,
                    onValueChange = {
                        numbersInput = it
                        error = null
                    },
                    label = { Text("(e.g., 4, 6, 8, 10)", fontSize = 12.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )

                Spacer(Modifier.height(12.dp))

                Text("Enter target value for Z-score", fontSize = 12.sp)

                Spacer(Modifier.height(6.dp))

                OutlinedTextField(
                    value = targetInput,
                    onValueChange = {
                        targetInput = it
                        error = null
                    },
                    label = { Text("Target value (x)") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )

                error?.let {
                    Spacer(Modifier.height(6.dp))
                    Text(text = it, color = MaterialTheme.colorScheme.error)
                }

                Spacer(Modifier.height(12.dp))

                Button(
                    onClick = {
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
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Calculate Z-Score")
                }
            }
        }

        Spacer(Modifier.height(20.dp))

        // Result Card & Steps
        zScoreResult?.let { z ->
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
                    Text("Z-Score Result", style = MaterialTheme.typography.titleSmall)

                    Text(
                        text = "%.4f".format(z),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        buildAnnotatedString {
                            append("The Z-score of ${targetInput} is ")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("%.4f".format(z))
                            }
                            append(", which means it is ")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("%.4f".format(z))
                            }
                            append(" standard deviations below the mean.")
                        },
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )

                    Spacer(Modifier.height(12.dp))

                    // Step-by-step LazyColumn
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 400.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val mean = numbers.sum() / numbers.size
                        val deviations = numbers.map { it - mean }
                        val squaredDevs = deviations.map { it * it }
                        val variance = squaredDevs.sum() / numbers.size
                        val sd = sqrt(variance)

                        item { Text("Step-By-Step Solution", style = MaterialTheme.typography.titleMedium) }

                        item {
                            StepCard(
                                "Step 1: List all numbers",
                                "Numbers: $numbersInput"
                            )
                        }

                        item {
                            StepCard(
                                "Step 2: Count the numbers",
                                "Population size (N) = ${numbers.size}"
                            )
                        }

                        item {
                            StepCard(
                                "Step 3: Calculate the mean (μ)",
                                "μ = (${numbers.joinToString(" + ")}) ÷ ${numbers.size} = ${"%.4f".format(mean)}"
                            )
                        }

                        item {
                            StepCard(
                                "Step 4: Calculate deviations from mean (x - μ)",
                                deviations.mapIndexed { idx, dev ->
                                    "${numbers[idx]} - ${"%.4f".format(mean)} = ${"%.4f".format(dev)}"
                                }.joinToString("\n")
                            )
                        }

                        item {
                            StepCard(
                                "Step 5: Square each deviation (x - μ)²",
                                squaredDevs.mapIndexed { idx, sq ->
                                    "(${deviations[idx].let { "%.4f".format(it) }})² = ${"%.4f".format(sq)}"
                                }.joinToString("\n")
                            )
                        }

                        item {
                            StepCard(
                                "Step 6: Calculate variance (σ²)",
                                "σ² = Σ(x - μ)² ÷ N = ${"%.4f".format(squaredDevs.sum())} ÷ ${numbers.size} = ${"%.4f".format(variance)}"
                            )
                        }

                        item {
                            StepCard(
                                "Step 7: Calculate standard deviation (σ)",
                                "σ = √variance = √${"%.4f".format(variance)} = ${"%.4f".format(sd)}"
                            )
                        }

                        item {
                            StepCard(
                                "Step 8: Calculate Z-score",
                                "Z = (x - μ) ÷ σ = (${x} - ${"%.4f".format(mean)}) ÷ ${"%.4f".format(sd)} = ${"%.4f".format(z)}"
                            )
                        }
                    }
                }
            }
        }
    }
}
