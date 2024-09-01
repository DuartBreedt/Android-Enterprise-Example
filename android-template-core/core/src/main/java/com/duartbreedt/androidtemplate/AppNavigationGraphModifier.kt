package com.duartbreedt.androidtemplate

import androidx.navigation.NavController

interface AppNavigationGraphModifier {
    fun addGraph(navController: NavController)
}