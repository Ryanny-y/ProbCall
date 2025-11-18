package com.example.probcal.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.probcal.page.HomeComp
import com.example.probcal.pages.MeanCalc
import com.example.probcal.pages.PopulationVarianceCalc
import com.example.probcal.pages.SampleVarianceCalc
import com.example.probcal.pages.ZScoreCalculatorCalc

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
                        "Sample Variance Calculator" -> navController.navigate("sample_variance_calculator")
                        "Z-Score Calculator" -> navController.navigate("z_score")
                        "Z-Score Area Calculator" -> navController.navigate("z_score_area")
                    }
                }
            )
        }

        composable("mean_calculator") {
            MeanCalc(modifier)
        }
        composable("population_variance_calculator") {
            PopulationVarianceCalc(modifier)
        }
        composable("sample_variance_calculator") {
            SampleVarianceCalc(modifier)
        }
        composable("z_score") {
            ZScoreCalculatorCalc(modifier)
        }
    }
}
