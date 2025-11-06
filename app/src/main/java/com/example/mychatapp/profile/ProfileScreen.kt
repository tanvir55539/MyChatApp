
package com.example.mychatapp.profile

import ChatBottomBar
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mychatapp.R


@Composable
fun ProfileScreen( viewModel: ProfileViewModel,
                  navController : NavController
) {

    LaunchedEffect(Unit) {
        viewModel.getCurrentUserData()
    }

    val context = LocalContext.current
    val userName by viewModel.currentUserName.collectAsState()
    val base64Image by viewModel.profileImageBase64.collectAsState()

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                viewModel.uploadProfilePicture(context, it)
            }
        }
    )



    Scaffold(
        topBar = {
            ProfileTopBar()
        },

        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF1976D2))
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
//                Text(
//                    text = "Profile",
//                    style = MaterialTheme.typography.titleLarge,
//                    color = Color.White,
//                    fontWeight = FontWeight.Bold
//                )

                Spacer(modifier = Modifier.height(24.dp))

                if (base64Image != null) {
                    val decodedBytes = Base64.decode(base64Image, Base64.DEFAULT)
                    val bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = "Profile Picture",
                        modifier =  Modifier.size(200.dp)
                            .clip(CircleShape)
                            .border(3.dp, Color.White, CircleShape)
                            .background(colorResource(id = R.color.logoColor), CircleShape)
                            .clickable { imagePicker.launch("image/*") }
                    )
                } else {
//                    Box(
//                        modifier = Modifier
//                            .size(200.dp)
//                            .background(Color.Gray)
//                            .clip(CircleShape)
//                            .border(3.dp, Color.White, CircleShape)
//                            .clickable { imagePicker.launch("image/*") },
//                        contentAlignment = Alignment.Center
//                    ) {
//                        Text("Tap to add", color = Color.White)
//                    }

                    Image(painter = painterResource(com.example.mychatapp.R.drawable.profileimg),
                        contentDescription = "Image",
                        modifier = Modifier.size(200.dp)
                            .clip(CircleShape)
                            .border(3.dp, Color.White, CircleShape)
                            .background(colorResource(id = R.color.logoColor), CircleShape)
                            .clickable { imagePicker.launch("image/*") }
                    )
                    Text(text = "Tap to add")
                }

                Spacer(modifier = Modifier.height(16.dp))

//                Text(
//                    text = userName,
//                    color = Color.White,
//                    fontWeight = FontWeight.Medium,
//                    fontSize = MaterialTheme.typography.titleLarge.fontSize
//                )
                ProfileUi(userName)

            }

        },
        bottomBar = {
            ChatBottomBar(navController)

        }

    )


}




@Composable
fun ProfileUi(userName: String){

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .size(width = 325.dp, height = 350.dp),

        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.profile)
        ),
        shape = RoundedCornerShape(16.dp)



    ) {

        Column(modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = userName,
                color = Color.White,
                fontWeight = FontWeight.Medium,
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )




        }


    }

}


@Composable
fun ProfileTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF2368D5))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Profile",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.White
        )
    }
}
