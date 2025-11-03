//package com.example.mychatapp
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Send
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.mychatapp.ui.theme.UiColor
//
//@Composable
//fun ChatUi(modifier: Modifier = Modifier){
//    Column(modifier = modifier.fillMaxSize().background(color = UiColor)) {
//        AppBar()
//        MessageUi()
//    }
//}
//
//
//@Composable
//fun MessageUi(){
//
//    var message by remember{
//        mutableStateOf("")
//    }
//
//Row(modifier = Modifier.padding(8.dp),
//    verticalAlignment = Alignment.CenterVertically
//) {
//
//    OutlinedTextField(
//    modifier=Modifier.weight(1f),
//    value = message,
//    onValueChange ={message = it} )
//
//    IconButton(onClick = {
//
//    }) {
//
//        Icon(imageVector = Icons.Default.Send,
//            contentDescription = "send button")
//    }
//}
//
//}
//
//
//
//@Composable
//fun AppBar(){
//    Row(modifier = Modifier.fillMaxWidth()
//        .background(MaterialTheme.colorScheme.surfaceVariant)
//        .padding(16.dp)
//        ) {
//        Text(text = "Chat App",
//            fontWeight = FontWeight.Bold,
//            fontSize = 20.sp)
//    }
//}