package com.example.probcal.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.probcal.ui.component.CalculatorCard

@Composable
fun HomeComp (modifier: Modifier) {
    val calculators = listOf(
        "Mean",
        "Variance",
        "Standard Deviation",
        "Z-Score"
    )

    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        items(calculators) { calc ->
            CalculatorCard(name = calc) {
                println("Clicked: $calc")
            }
        }
    }
}