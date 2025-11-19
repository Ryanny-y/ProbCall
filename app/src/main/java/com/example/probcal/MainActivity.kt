package com.example.probcal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.probcal.navigation.AppNavGraph
import com.example.probcal.page.HeaderBar
import com.example.probcal.ui.theme.ProbCalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProbCalTheme {
                val navController = rememberNavController();

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { HeaderBar(navController = navController) },
                ) { innerPadding ->
                    AppNavGraph(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController
                    )
                }
            }
        }
    }
}