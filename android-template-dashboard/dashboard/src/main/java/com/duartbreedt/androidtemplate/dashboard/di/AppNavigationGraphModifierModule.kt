package com.duartbreedt.androidtemplate.dashboard.di

import androidx.navigation.NavController
import com.duartbreedt.androidtemplate.AppNavigationGraphModifier
import com.duartbreedt.androidtemplate.addGraph
import com.duartbreedt.androidtemplate.dashboard.R
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoSet
import javax.inject.Inject

@Module
@InstallIn(ActivityComponent::class)
abstract class AppNavigationGraphModifierModule {

    @Binds
    @IntoSet
    abstract fun provideAppNavigationGraphModifier(integrateToAppNavGraphImpl: AppNavigationGraphModifierImpl): AppNavigationGraphModifier
}

class AppNavigationGraphModifierImpl @Inject constructor() : AppNavigationGraphModifier {

    override fun addGraph(navController: NavController) {
        navController.addGraph(R.navigation.dashboard_nav_graph)
    }
}