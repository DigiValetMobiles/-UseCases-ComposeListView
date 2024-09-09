package com.paragon.usecases_composelistview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.paragon.usecases_composelistview.ui.components.home.HomeDestination
import com.paragon.usecases_composelistview.ui.navigator.AppNavHost
import com.paragon.usecases_composelistview.ui.theme.UseCasesComposeListViewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UseCasesComposeListViewTheme {
                AppNavHost(
                    navController = rememberNavController(),
                    startDestination = HomeDestination.ROUTE,
                )
            }
        }
    }
}