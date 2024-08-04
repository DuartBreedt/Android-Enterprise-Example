package com.duartbreedt.androidtemplate

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment


fun FragmentActivity.getNavController(@IdRes navHost: Int): NavController =
    (supportFragmentManager.findFragmentById(navHost) as NavHostFragment).navController

