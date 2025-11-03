package com.example.mychatapp.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mychatapp.navigation.Routs
import com.example.mychatapp.R

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: AuthViewModel
){


        var email by remember {
            mutableStateOf("")
        }

        var pass by remember {
            mutableStateOf("")
        }

        val context = LocalContext.current
        val authState by viewModel.authSate.collectAsState()

        Column(modifier = Modifier.fillMaxSize().background(Color(0xFF1976D2)).padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally)
        {

           Image(painter = painterResource(R.drawable.chatapp),
               contentDescription = "Image",
               modifier = Modifier.size(200.dp)
                   .clip(CircleShape)
                   .border(3.dp, Color.White, CircleShape)
                   .background(colorResource(id = R.color.logoColor), CircleShape)
           )

            Text(text = "Welcome Back", fontSize = 28.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(
                value =email,
                onValueChange = {email = it},
                label = {Text(text = "Email")},
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = pass,
                onValueChange = {pass = it},
                label = {Text(text = "Password")},
                singleLine = true

            )

            Spacer(modifier = Modifier.height(16.dp))


            Button(onClick = {

                if(email.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(context,"Please fill the credential",Toast.LENGTH_SHORT).show()

                }
                else {
                    viewModel.login (
                        email = email,
                        password = pass,
                        onResult = { success, message ->
                            if (success) {
                                Toast.makeText(context, "Login Successfully", Toast.LENGTH_SHORT).show()

//                                navController.navigate(Routs.chatListScreen) // chang it to route later
                                navController.navigate(Routs.chatListScreen) {
                                    popUpTo(Routs.LoginScreen) { inclusive = true } // removes login screen from back stack
                                }
                            } else {

                                Toast.makeText(context, message ?: "Login fail", Toast.LENGTH_SHORT).show()

                            }

                        })
                }

            })
            {
                Text(text = "Login")
            }

            TextButton(onClick = {navController.navigate(Routs.SignUpScreen)}) {
                Text(text = "Do not have account? Register")
            }

        }


    if (authState is AuthState.Loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.White)
        }
    }





}