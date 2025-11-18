package com.example.probcal.page

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.probcal.ui.component.CalculatorCard

@Composable
fun HomeComp (
    modifier: Modifier = Modifier,
    onCalculatorClick: (String) -> Unit
) {
    val calculators = listOf(
        "Mean Calculator",
        "Population Variance Calculator",
        "Sample Variance Calculator",
        "Standard Deviation Calculator",
        "Z-Score Calculator"
    )

    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        items(calculators) { calc ->
            CalculatorCard(name = calc, onClick = { onCalculatorClick(calc) })
        }
    }
}