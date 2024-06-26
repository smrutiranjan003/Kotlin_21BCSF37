package com.example.firebaseapplication

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.firebaseapplication.ui.theme.FirebaseApplicationTheme

@Composable
fun SuccessScreen(navController: NavHostController, viewModel: AuthViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Logged in successfully")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            viewModel.signOut()
            navController.navigate("auth") {
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = true
                }
            }
        }) {
            Text("Sign Out")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SuccessScreenPreview() {
    FirebaseApplicationTheme {
        SuccessScreen(navController = rememberNavController(), viewModel = AuthViewModel())
    }
}
