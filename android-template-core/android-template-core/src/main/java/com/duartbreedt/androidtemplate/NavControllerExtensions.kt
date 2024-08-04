package com.duartbreedt.androidtemplate

import android.content.Context
import android.content.Intent
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentActivity
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import kotlin.reflect.KClass

fun <T : FragmentActivity> NavController.createActivityDestination(
    activity: KClass<T>,
    @IdRes navigationId: Int,
    @StringRes deeplink: Int,
    context: Context
): ActivityNavigator.Destination =
    navigatorProvider.getNavigator(ActivityNavigator::class.java).createDestination().apply {
        setIntent(Intent(context, activity.java))
        id = navigationId
        addDeepLink(context.getString(deeplink))
    }