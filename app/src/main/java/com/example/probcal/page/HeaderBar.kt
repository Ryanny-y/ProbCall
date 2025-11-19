package com.example.probcal.page

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HeaderBar(
    navController: NavController,
    modifier: Modifier = Modifier,
    title: String = "ProbCal"
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 28.dp,
                start = 10.dp,
                end = 10.dp
            )
            .height(40.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            modifier = modifier
                .clickable {
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                },
            text = title,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 28.sp,
        )
    }
}
