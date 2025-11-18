package com.example.probcal.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HeaderBar(
    modifier: Modifier = Modifier,
    title: String = "ProbCal"
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 20.dp,
                start = 10.dp,
                end = 10.dp
            )
            .height(60.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 24.sp
        )
    }
}