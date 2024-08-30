package com.duartbreedt.androidtemplate

import android.net.Uri
import android.view.View.NO_ID
import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.navigation.NavController
import androidx.navigation.NavOptions

fun NavController.navigateToRes(@IdRes resId: Int) {
    navigate(resId, args = null, navOptions = buildNavOptions())
}

fun NavController.navigateToDeeplink(uriString: String, @IdRes popUpToRes: Int = NO_ID) {
    val uriBuilder = Uri.parse(uriString).buildUpon().clearQuery()
    val uri = uriBuilder.build()

    navigate(uri, navOptions = buildNavOptions(popUpToRes))
}

fun NavController.addGraph(@NavigationRes resId: Int) {
    graph.addAll(navInflater.inflate(resId))
}

private fun buildNavOptions(@IdRes popUpToRes: Int = NO_ID, inclusive: Boolean = true): NavOptions = NavOptions
    .Builder()
    .setEnterAnim(R.anim.slide_in_right)
    .setExitAnim(R.anim.do_nothing)
    .setPopExitAnim(R.anim.slide_out_right)
    .setPopEnterAnim(R.anim.do_nothing)
    .setPopUpTo(popUpToRes, inclusive = inclusive)
    .build()
