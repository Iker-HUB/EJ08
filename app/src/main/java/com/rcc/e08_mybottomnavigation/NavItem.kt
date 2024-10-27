package com.rcc.e08_mybottomnavigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
    val screen: Screen,
    val label: String,
    val icon: ImageVector
)