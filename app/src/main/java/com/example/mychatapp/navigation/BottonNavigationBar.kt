import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.mychatapp.navigation.Routs

@Composable
fun ChatBottomBar(
    navController: NavController,
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
            .background(Color(0xFF014E86))
//            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(16.dp)
            ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center

    ) {

        IconButton(onClick = {
            if (currentRoute != Routs.chatListScreen) {
                navController.navigate(Routs.chatListScreen)
            }
        }) {

            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Send",
                tint = Color.White,
                modifier = Modifier.size(30.dp)

            )
//
        }

        Spacer(modifier = Modifier.width(80.dp))

        IconButton(onClick = {
            if(currentRoute != Routs.ProfileScreen){
                navController.navigate(Routs.ProfileScreen)
            }

        }) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Send",
                tint = Color.White,
                modifier = Modifier.size(30.dp)

            )
        }

        Spacer(modifier = Modifier.width(80.dp))

        IconButton(onClick = {
            if(currentRoute != Routs.SettingsScreen){
                navController.navigate(Routs.SettingsScreen)
            }
        }) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Send",
                tint = Color.White,
                modifier = Modifier.size(30.dp)

            )
        }
    }
}





//
//import androidx.compose.animation.animateColorAsState
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material.icons.filled.Person
//import androidx.compose.material.icons.filled.Settings
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.draw.shadow
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.navigation.compose.currentBackStackEntryAsState
//import com.example.mychatapp.navigation.Routs
//
//@Composable
//fun ChatBottomBar(
//    navController: NavController
//) {
//    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
//
//    val gradientBackground = Brush.horizontalGradient(
//        colors = listOf(
//            Color(0xFF0072FF), // Bright blue
//            Color(0xFF00C6FF)  // Cyan blue
//        )
//    )
//
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 12.dp, vertical = 10.dp),
//        contentAlignment = Alignment.Center
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth(0.9f)
//                .clip(RoundedCornerShape(50.dp))
//                .shadow(
//                    elevation = 10.dp,
//                    shape = RoundedCornerShape(50.dp),
//                    ambientColor = Color.Black.copy(alpha = 0.4f),
//                    spotColor = Color.Black.copy(alpha = 0.4f)
//                )
//                .background(gradientBackground)
//                .padding(vertical = 10.dp, horizontal = 24.dp),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            BottomBarIcon(
//                icon = Icons.Default.Home,
//                description = "Home",
//                isSelected = currentRoute == Routs.chatListScreen,
//                onClick = {
//                    if (currentRoute != Routs.chatListScreen) {
//                        navController.navigate(Routs.chatListScreen)
//                    }
//                }
//            )
//
//            BottomBarIcon(
//                icon = Icons.Default.Person,
//                description = "Profile",
//                isSelected = currentRoute == Routs.ProfileScreen,
//                onClick = {
//                    if (currentRoute != Routs.ProfileScreen) {
//                        navController.navigate(Routs.ProfileScreen)
//                    }
//                }
//            )
//
//            BottomBarIcon(
//                icon = Icons.Default.Settings,
//                description = "Settings",
//                isSelected = currentRoute == Routs.SettingsScreen,
//                onClick = {
//                    if (currentRoute != Routs.SettingsScreen) {
//                        navController.navigate(Routs.SettingsScreen)
//                    }
//                }
//            )
//        }
//    }
//}
//
//@Composable
//fun BottomBarIcon(
//    icon: androidx.compose.ui.graphics.vector.ImageVector,
//    description: String,
//    isSelected: Boolean,
//    onClick: () -> Unit
//) {
//    val iconTint by animateColorAsState(
//        targetValue = if (isSelected) Color.White else Color.White.copy(alpha = 0.6f),
//        label = "iconTint"
//    )
//
//    val iconBackground by animateColorAsState(
//        targetValue = if (isSelected) Color.White.copy(alpha = 0.25f) else Color.Transparent,
//        label = "iconBg"
//    )
//
//    Box(
//        modifier = Modifier
//            .size(45.dp)
//            .clip(CircleShape)
//            .background(iconBackground),
//        contentAlignment = Alignment.Center
//    ) {
//        IconButton(onClick = onClick) {
//            Icon(
//                imageVector = icon,
//                contentDescription = description,
//                tint = iconTint,
//                modifier = Modifier.size(26.dp)
//            )
//        }
//    }
//}
