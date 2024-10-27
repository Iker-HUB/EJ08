package com.rcc.e08_mybottomnavigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object HomeScreen: Screen()

    @Serializable
    data object SettingsScreen: Screen()

    @Serializable
    data class ProfileScreen(val userId: String): Screen()
}