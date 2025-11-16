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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mychatapp.R
import com.example.mychatapp.navigation.Routs

@Composable
fun RegistrationScreen(
    navController: NavController,
    viewModel : AuthViewModel = viewModel()
){

    var name by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }

    var pass by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
    val authState by viewModel.authSate.collectAsState()


    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).padding(16.dp),
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

            Text(text = "Do Registration 🙂", fontSize = 28.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(4.dp))


            OutlinedTextField(
                value = name,
                onValueChange = {name = it},
                label = {Text(text = "Full Name")},
                singleLine = true,
                shape = RoundedCornerShape(18.dp)

            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value =email,
                onValueChange = {email = it},
                label = {Text(text = "Email")},
                singleLine = true,
                shape = RoundedCornerShape(18.dp)

            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = pass,
                onValueChange = {pass = it},
                label = {Text(text = "Password")},
                singleLine = true,
                shape = RoundedCornerShape(18.dp)


            )

            Spacer(modifier = Modifier.height(16.dp))


            Button(onClick = {

                if(email.isEmpty() || name.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(context,"Please fill the credential",Toast.LENGTH_SHORT).show()

                }
                else {
                    viewModel.signUp(
                        name = name,
                        email = email,
                        password = pass,
                        onResult = { success, message ->
                            if (success) {

                                Toast.makeText(context, "Registration Successful", Toast.LENGTH_SHORT).show()
                                navController.navigate(Routs.LoginScreen) // chang it to route later

                            } else {
                                Toast.makeText(context, message ?: "Registration fail", Toast.LENGTH_SHORT).show()
                            }

                        })
                }

            })
            {
                Text(text = "Register")
            }

            TextButton(onClick = {navController.navigate(Routs.LoginScreen)}) {
                Text(text = "Already have an account? Login")
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

