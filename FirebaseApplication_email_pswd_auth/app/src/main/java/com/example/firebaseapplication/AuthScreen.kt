//AuthScreen.kt

package com.example.firebaseapplication

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.firebaseapplication.ui.theme.FirebaseApplicationTheme

@Composable
fun AuthScreen(navController: NavHostController, viewModel: AuthViewModel = viewModel()) {
    val authState by viewModel.authState.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showSignUpSuccessDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        when (authState) {
            is AuthState.Loading -> CircularProgressIndicator()
            is AuthState.Success -> {
                if ((authState as AuthState.Success).user == null && showSignUpSuccessDialog) {
                    AlertDialog(
                        onDismissRequest = {
                            showSignUpSuccessDialog = false
                        },
                        title = { Text("Signed up successfully!") },
                        confirmButton = {
                            Button(
                                onClick = {
                                    showSignUpSuccessDialog = false
                                    email = ""
                                    password = ""
                                }
                            ) {
                                Text("OK")
                            }
                        }
                    )
                } else if ((authState as AuthState.Success).user != null) {
                    navController.navigate("success")
                }
            }
            is AuthState.Error -> Text("Error: ${(authState as AuthState.Error).message}", color = MaterialTheme.colorScheme.error)
            else -> Unit
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.signIn(email, password) }) {
            Text("Sign In")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            viewModel.signUp(email, password)
            showSignUpSuccessDialog = true
        }) {
            Text("Sign Up")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    FirebaseApplicationTheme {
        AuthScreen(navController = rememberNavController())
    }
}
