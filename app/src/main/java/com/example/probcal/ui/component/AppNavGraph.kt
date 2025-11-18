package com.example.probcal.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.probcal.page.HomeComp
import com.example.probcal.pages.MeanCalculatorPage
import com.example.probcal.pages.PopulationVarianceCalc

@Composable
fun AppNavGraph(modifier: Modifier, navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeComp(
                modifier = modifier,
                onCalculatorClick = { selected ->
                    when (selected) {
                        "Mean Calculator" -> navController.navigate("mean_calculator")
                        "Population Variance Calculator" -> navController.navigate("population_variance_calculator")
                    }
                }
            )
        }

        composable("mean_calculator") {
            MeanCalculatorPage(modifier)
        }
        composable("population_variance_calculator") {
            PopulationVarianceCalc(modifier)
        }
    }
}
