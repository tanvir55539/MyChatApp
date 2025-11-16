package com.example.mychatapp.settings

import ChatBottomBar
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mychatapp.R
import com.example.mychatapp.auth.AuthViewModel
import com.example.mychatapp.navigation.Routs


@Composable
fun SettingsScreen(navController : NavController,authViewModel: AuthViewModel){
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            SettingsTopBar()
        },

        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF25294F), // your color
                            Color(0xFF3A2F63),
                            Color(0xFF533C78),
                            Color(0xFF7C54AA),
                            Color(0xFF633B93)

                        )
                    ))
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(8.dp))

                DarkModeSwitch()
                Spacer(modifier = Modifier.height(8.dp))

                LogoutButton(authViewModel,navController)

            }

        },
        bottomBar = {
            ChatBottomBar(navController)

        }

    )

}



@Composable
fun DarkModeSwitch() {

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .size(width = 360.dp, height = 56.dp)

            .clickable{},

        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF633B93)
        ),
        shape = RoundedCornerShape(16.dp)

    ) {

        Row(
            modifier = Modifier.padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,

            ) {

            Text(
                text = "Dark Mode",
                color = Color.White,
                fontWeight = FontWeight.W300,
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )

            Spacer(modifier = Modifier.weight(1f))

            val checkedState = remember { mutableStateOf(true) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it }
            )

        }
    }

}


@Composable
fun LogoutButton(authViewModel: AuthViewModel,navController: NavController) {
    var showDialog by remember { mutableStateOf(false) }
    ElevatedCard(

        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .size(width = 360.dp, height = 56.dp)

            .clickable{

                showDialog =true

            },

        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF633B93)
        ),
        shape = RoundedCornerShape(16.dp)

    ) {

        Row(
            modifier = Modifier.padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,

            ) {

            Text(
                text = "Log Out",
                color = Color.White,
                fontWeight = FontWeight.W300,
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )

            Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(R.drawable.logout),
            contentDescription = "Logout icon",
            modifier = Modifier.size(24.dp)
        )
            if (showDialog){
                LogOutDialog(
                    onDismiss = {showDialog = false},
                    onConfirm = {
                        showDialog = false
                        authViewModel.loguot()
                        navController.navigate(Routs.LoginScreen){
                            popUpTo(navController.graph.id){
                                inclusive = false
                            }
                        }


                    }
                )
            }

        }
    }

}



@Composable
fun LogOutDialog(
    onDismiss : () -> Unit,
    onConfirm : () -> Unit
) {

    AlertDialog(
        onDismissRequest = {
          onDismiss()
        },
        title = {
            Text(text = "Are you sure you want to log out? ")
        },
        text = {
            Text("confirm your action: ")
        },
        confirmButton = {
            Button(

                onClick = {
                   onConfirm()
                }) {
                Text("Log Out")
            }
        },
        dismissButton = {
            Button(

                onClick = {
                    onDismiss()

                }) {
                Text("Cancel")
            }
        }
    )
}






@Composable
fun SettingsTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF2368D5))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Settings",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.White
        )
    }
}