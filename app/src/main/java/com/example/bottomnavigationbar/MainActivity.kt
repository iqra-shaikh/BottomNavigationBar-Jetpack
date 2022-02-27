package com.example.bottomnavigationbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigationbar.ui.theme.BottomNavigationBarTheme

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomNavigationBarTheme {
                    val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(items = listOf(
                            BottomNavItem(
                                name = "Home",
                                route = "home",
                                icon = Icons.Filled.Home
                            ),
                            BottomNavItem(
                                name = "Search",
                                route = "search",
                                icon = Icons.Default.Search
                            ),
                            BottomNavItem(
                                name = "Reels",
                                route = "reels",
                                icon = Icons.Filled.MovieFilter
                            ),
                                BottomNavItem(
                                    name = "Likes",
                                    route = "likes",
                                    icon = Icons.Filled.Favorite,
                                    badgeCount = 100
                        ),
                                    BottomNavItem(
                                        name = "Profile",
                                        route = "profile",
                                        icon = Icons.Filled.AccountCircle
                                        ),
                        ),
                            navController = navController,
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                ) {
                    Navigation(navController = navController)
                }

            }
        }

    }
}


@Composable
fun Navigation(navController: NavHostController) {
NavHost(navController = navController, startDestination = "home") {
    composable("home") {
        HomeScreen()
    }
    composable("search") {
        Search()
    }
    composable("reels") {
        Reels()
    }
    composable("likes") {
        Likes()
    }
    composable("profile") {
        Profile()
    }
}
}
@ExperimentalMaterialApi
@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.DarkGray,
        elevation = 5.dp
        ) {
            items.forEach{item ->
                val selected = item.route == backStackEntry.value?.destination?.route
                BottomNavigationItem(
                    selected = item.route == navController.currentDestination?.route,
                    onClick = { onItemClick(item) },
                    selectedContentColor = Color.Black,
                    unselectedContentColor = Color.White,
                    icon =  {
                        Column(horizontalAlignment = CenterHorizontally) {
                            if (item.badgeCount > 0) {
                                BadgeBox(
                                    badgeContent = {
                                        Text(text = item.badgeCount.toString())
                                    }
                                ) {
                                        Icon(imageVector = item.icon,
                                            contentDescription = item.name)
                                } } else {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.name
                                )
                            }
                                if (selected) {
                                    Text(text = item.name,
                                    textAlign = TextAlign.Center,
                                    fontSize = 10.sp)
                                }
                            }
                        }
                )
        }
    }

}

@Composable
fun HomeScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Home Screen")
    }
}
@Composable
fun Search() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Search")
    }
}
@Composable
fun Reels() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Reels")
    }
}
@Composable
    fun Likes() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Likes")
    }
}
@Composable
        fun Profile() {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Profile")
            }
}

