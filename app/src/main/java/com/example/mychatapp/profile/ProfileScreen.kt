//package com.example.mychatapp.profile
//
//import android.util.Log
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.mychatapp.R
//import androidx.compose.runtime.getValue
//import com.example.mychatapp.auth.AuthViewModel
//import com.example.mychatapp.data.model.userModel
//import androidx.activity.result.ActivityResultLauncher
//import androidx.compose.ui.platform.LocalContext
//import coil.compose.AsyncImage
//
////import coil3.compose.rememberAsyncImagePainter
//
//@Composable
//fun ProfileScreen(profileViewModel: ProfileViewModel
//) {
//    val name by profileViewModel.currentUserName.collectAsState()
//    val base64Image by profileViewModel.profileImageBase64.collectAsState()
//
//    val pickMedia = rememberLauncherForActivityResult(PickVisualMedia()) { uri ->
//        if (uri != null) {
//            // Pass context because decoding needs it
//            profileViewModel.uploadProfilePicture(LocalContext.current, uri)
//        }
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFF2368D5)),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        val imageModel = if (base64Image != null) {
//            "data:image/jpeg;base64,$base64Image"
//        } else {
//            R.drawable.bottombarprofile
//        }
//
//        AsyncImage(
//            model = imageModel,
//            contentDescription = "Profile Image",
//            modifier = Modifier
//                .size(200.dp)
//                .clip(CircleShape)
//                .border(3.dp, Color.White, CircleShape)
//                .clickable {
//                    pickMedia.launch(PickVisualMedia.ImageOnly)
//                }
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//        Text(text = name, color = Color.White, fontSize = 24.sp)
//    }
//}

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
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController


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
        topBar = {},

        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF1976D2))
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Profile",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(24.dp))

                if (base64Image != null) {
                    val decodedBytes = Base64.decode(base64Image, Base64.DEFAULT)
                    val bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(120.dp)
                            .clickable { imagePicker.launch("image/*") }
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .background(Color.Gray)
                            .clickable { imagePicker.launch("image/*") },
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Tap to add", color = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = userName,
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                )


            }

        },
        bottomBar = {
            ChatBottomBar(navController)

        }

    )


}
