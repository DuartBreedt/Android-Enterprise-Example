package com.duartbreedt.androidtemplate

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Singleton

//@Module
//@InstallIn(ActivityComponent::class)
//class NavControllerModule {
//
//    @Provides
//    fun providesNavController(@ActivityContext context: Context): NavController {
//        return (context as FragmentActivity).getNavController(R.id.app_nav_host)
//    }
//}