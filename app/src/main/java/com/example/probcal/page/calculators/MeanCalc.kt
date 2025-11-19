package com.example.probcal.page.calculators

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.probcal.ui.component.CalculatorScreen
import com.example.probcal.ui.component.StepCard

@Composable
fun MeanCalc(modifier: Modifier = Modifier) {

    var input by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<Double?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    val numbers = input
        .split(",")
        .mapNotNull { num -> num.trim().toDoubleOrNull() }

    CalculatorScreen (
        modifier = modifier,
        title = "Mean Calculator",
        description = "Calculate the arithmetic mean of your dataset.",
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
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
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )

                error?.let {
                    Spacer(Modifier.height(6.dp))
                    Text(text = it, color = MaterialTheme.colorScheme.error)
                }

                Spacer(Modifier.height(12.dp))

                Button(
                    onClick = {
                        if (numbers.isEmpty()) {
                            error = "Please enter valid numbers."
                            result = null
                        } else {
                            result = numbers.sum() / numbers.size
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Calculate")
                }
            }
        }

        Spacer(Modifier.height(20.dp))

        result?.let { value ->
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
                    Text("Result", style = MaterialTheme.typography.titleSmall)

                    Text(
                        text = "%.4f".format(value),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        buildAnnotatedString {
                            append("The mean of the given dataset is ")

                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("%.4f".format(value))
                            }
                        },
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                    )
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 300.dp)
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    item {
                        Text("Step-By-Step Solution", style = MaterialTheme.typography.titleMedium)
                    }

                    item {
                        StepCard(
                            title = "Step 1: List all numbers",
                            content = "Numbers: $input"
                        )
                    }

                    item {
                        StepCard(
                            title = "Step 2: Count the numbers",
                            content = "Total Count: ${numbers.size}"
                        )
                    }

                    item {
                        StepCard(
                            title = "Step 3: Calculate the sum",
                            content = "Sum: ${numbers.sum()}"
                        )
                    }

                    item {
                        StepCard(
                            title = "Step 4: Divide sum by count",
                            content = "Mean = Sum ÷ n = ${numbers.sum()} ÷ ${numbers.size} = ${"%.4f".format(value)}"
                        )
                    }
                }
            }
        }
    }
}

