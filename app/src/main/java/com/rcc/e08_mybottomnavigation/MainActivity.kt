package com.rcc.e08_mybottomnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rcc.e08_mybottomnavigation.ui.theme.E08MyBottomNavigationTheme
import com.rcc.e08_mybottomnavigation.ui.theme.Pink40
import com.rcc.e08_mybottomnavigation.ui.theme.Pink80

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            E08MyBottomNavigationTheme {
                MyScaffold()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScaffold() {
    val navController = rememberNavController()

    val tabs = listOf(
        NavItem(
            label = "Home",
            icon = Icons.Filled.Home,
            screen = Screen.HomeScreen,
        ),
        NavItem(
            label = "Pantalla2",
            icon = Icons.Filled.Settings,
            screen = Screen.SettingsScreen,
        ),
        NavItem(
            label = "Pantalla3",
            icon = Icons.Filled.Person,
            screen = Screen.ProfileScreen("newText"),
        )
    )

    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text("Top app bar")
                },
                colors = topAppBarColors(
                    containerColor = Pink80,
                    titleContentColor = Pink40,
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Pink80,
                contentColor = Pink40,
            ) {
                tabs.mapIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = selectedTabIndex == index,
                        onClick = {
                            selectedTabIndex = index
                            navController.navigate(navItem.screen)
                        },
                        icon = {
                            Icon(
                                tint = Pink40,
                                imageVector = navItem.icon,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(
                                color = Pink40,
                                text = navItem.label
                            )
                        }
                    )
                }
            }
        }

    ) { innerPadding ->
        var textoPantalla by rememberSaveable { mutableStateOf("") }
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(
                navController = navController,
                startDestination = Screen.HomeScreen
            ) {
                composable<Screen.HomeScreen> {
                    var text by rememberSaveable { mutableStateOf("") }
                    Column(
                        modifier = Modifier.fillMaxSize(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        Row() {
                            Text(
                                text = "Pantalla 1",
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                color = Color.Red,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        Spacer(modifier = Modifier.height(100.dp));

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            TextField(
                                value = text,
                                onValueChange = {
                                    text = it
                                    textoPantalla = text
                                },
                                label = {
                                    Text("Introducir Texto")
                                },
                                shape = RoundedCornerShape(8.dp),
                                colors = TextFieldDefaults.textFieldColors(
                                    focusedTextColor = Color.Black,
                                    containerColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    focusedLabelColor = Color(0xFF6200EE),
                                    unfocusedLabelColor = Color.Gray
                                ),
                                modifier = Modifier
                                    .width(250.dp)
                                    .border(2.dp, Color(0xFF6200EE), RoundedCornerShape(8.dp))
                            )
                        }
                        Spacer(modifier = Modifier.height(100.dp));
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(
                                onClick = {
                                    selectedTabIndex = 1
                                    navController.navigate(Screen.SettingsScreen)
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White,
                                    contentColor = Color.Black
                                ),
                                shape = RoundedCornerShape(0.dp)
                            ) {
                                Text("Enviar")
                            }
                        }
                    }
                }

                composable<Screen.SettingsScreen> {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = textoPantalla,
                            color = Color.Red
                        )
                    }
                }
                composable<Screen.ProfileScreen> {
                    textoPantalla = "";
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Pantalla 3",
                            color = Color.Red
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview() {
    E08MyBottomNavigationTheme {
        MyScaffold()
    }
}