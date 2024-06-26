package com.example.firebaseapplication

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebaseapplication.ui.theme.FirebaseApplicationTheme

@Composable
fun MainScreen(viewModel: AuthViewModel = viewModel()) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "auth") {
        composable("auth") {
            AuthScreen(navController, viewModel)
        }
        composable("success") {
            SuccessScreen(navController, viewModel)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    FirebaseApplicationTheme {
        MainScreen()
    }
}
