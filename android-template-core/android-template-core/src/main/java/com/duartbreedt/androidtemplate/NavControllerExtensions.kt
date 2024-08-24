package com.duartbreedt.androidtemplate

import android.net.Uri
import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.navigation.NavController
import androidx.navigation.NavOptions

fun NavController.navigateToRes(@IdRes resId: Int) {
    navigate(resId, args = null, navOptions = buildNavOptions())
}

fun NavController.navigateToDeeplink(uriString: String) {
    val uriBuilder = Uri.parse(uriString).buildUpon().clearQuery()
    val uri = uriBuilder.build()

    navigate(uri, navOptions = buildNavOptions())
}

fun NavController.addGraph(@NavigationRes resId: Int) {
    graph.addAll(navInflater.inflate(resId))
}

private fun buildNavOptions(): NavOptions = NavOptions
    .Builder()
    .setEnterAnim(R.anim.slide_in_right)
    .setExitAnim(R.anim.do_nothing)
    .setPopExitAnim(R.anim.slide_out_right)
    .setPopEnterAnim(R.anim.do_nothing)
    .build()
