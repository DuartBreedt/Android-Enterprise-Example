package com.duartbreedt.androidtemplate.registration.di

import android.content.Context
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import com.duartbreedt.androidtemplate.AppNavigationGraphModifier
import com.duartbreedt.androidtemplate.createActivityDestination
import com.duartbreedt.androidtemplate.registration.R
import com.duartbreedt.androidtemplate.registration.RegistrationActivity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.multibindings.IntoSet
import javax.inject.Inject

import com.duartbreedt.androidtemplate.registration.data.R as DataR

@Module
@InstallIn(ActivityComponent::class)
abstract class AppNavigationGraphModifierModule {

    @Binds
    @IntoSet
    abstract fun provideAppNavigationGraphModifier(integrateToAppNavGraphImpl: AppNavigationGraphModifierImpl): AppNavigationGraphModifier
}

class AppNavigationGraphModifierImpl @Inject constructor(
    @ActivityContext val context: Context
) : AppNavigationGraphModifier {

    override fun addActivity(navController: NavController) {

        val newDestination: ActivityNavigator.Destination = navController.createActivityDestination(
            RegistrationActivity::class, R.id.registrationActivity, DataR.string.deeplink_registration_landing, context
        )

        navController.graph.addDestination(newDestination)
    }
}