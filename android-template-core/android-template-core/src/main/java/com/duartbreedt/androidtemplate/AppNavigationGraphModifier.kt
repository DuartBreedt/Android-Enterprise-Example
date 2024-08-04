package com.duartbreedt.androidtemplate

import androidx.navigation.NavController

interface AppNavigationGraphModifier {
    fun addActivity(navController: NavController)
}