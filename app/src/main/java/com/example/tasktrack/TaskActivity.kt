package com.example.tasktrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tasktrack.TaskActivityContent.homeScreen.ui.HomeScreen
import com.example.tasktrack.TaskActivityContent.insights.ui.InsightsScreen
import com.example.tasktrack.TaskActivityContent.settings.ui.SettingsScreen
import com.example.tasktrack.TaskActivityContent.taskScreen.ui.TaskScreen
import com.example.tasktrack.ui.theme.TaskTrackTheme

class TaskActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val backStackEntry by navController.currentBackStackEntryAsState()
            var showBottomBar by rememberSaveable { mutableStateOf(true) }
            showBottomBar = when (backStackEntry?.destination?.route) {
                "settingscategory" -> true
                else -> true
            }




            TaskTrackTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    bottomBar = {
                        if (showBottomBar) {
                            NavigationBar(
                                containerColor = NavigationBarDefaults.containerColor,
                                tonalElevation = NavigationBarDefaults.Elevation,
                                windowInsets = NavigationBarDefaults.windowInsets

                            ) {
                                NavigationBarItem(
                                    selected = backStackEntry?.destination?.route == "HomeScreen",
                                    onClick = { navController.navigate("HomeScreen") },
                                    label = {
                                        Text("Home")
                                    },
                                    icon = {
                                        Icon(painterResource(id = R.drawable.home), contentDescription = "home", Modifier.size(24.dp))
                                    },
                                    interactionSource = MutableInteractionSource()
                                )

                                NavigationBarItem(
                                    selected = backStackEntry?.destination?.route == "TaskScreen",
                                    onClick = { navController.navigate("TaskScreen") },
                                    label = {
                                        Text("Task")
                                    },
                                    icon = { Icon(painterResource(id = R.drawable.tasks), contentDescription = "tasks", Modifier.size(24.dp)) }
                                )

                                NavigationBarItem(
                                    selected = backStackEntry?.destination?.route == "InsightScreen",
                                    onClick = { navController.navigate("InsightScreen") },
                                    label = {
                                        Text(text ="Insights" , style = MaterialTheme.typography.bodySmall)
                                    },
                                    icon = { Icon(painterResource(id = R.drawable.insights), contentDescription = "insights", Modifier.size(24.dp)) }
                                )

                                NavigationBarItem(
                                    selected = backStackEntry?.destination?.route?.startsWith("SettingsScreen") ?: false,

                                    onClick = { navController.navigate("SettingsScreen") },
                                    label = {
                                        Text("Setting", fontFamily = FontFamily())
                                    },
                                    icon = { Icon(painterResource(id = R.drawable.settings), contentDescription = "settings", Modifier.size(24.dp)) }
                                )
//
                            }
                        }
                    },
                    content = { innerPadding ->
                        NavHost(navController = navController, startDestination = "HomeScreen") {
                            composable("HomeScreen") {
                                Surface(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding)
                                ) {
                                    HomeScreen(navController = navController)
                                }
//
                            }
                            composable("TaskScreen") {
                                Surface(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding)
                                ) {
                                    TaskScreen(navController = navController)
                                }
                            }
                            composable("InsightScreen") {
                                Surface(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding)
                                ) {
                                    InsightsScreen(navController)
                                }
                            }
                            composable("SettingsScreen") {
                                Surface(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding)
                                ) {
                                    SettingsScreen(navController = navController)
                                }
                            }
                        }
                    }

                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TaskTrackTheme {
        Greeting("Android")
    }
}
