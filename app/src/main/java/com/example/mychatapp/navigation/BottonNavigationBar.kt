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
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(Color(0xFF005CA1))
            .padding(24.dp),
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

