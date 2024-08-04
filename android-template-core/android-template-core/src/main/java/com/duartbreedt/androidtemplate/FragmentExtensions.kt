package com.duartbreedt.androidtemplate

import android.net.Uri
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController

fun Fragment.navigate(@IdRes resId: Int) {
    findNavController().navigate(resId, args = null, navOptions = buildNavOptions())
}

fun Fragment.navigate(uri: String) {
    val uriBuilder = Uri.parse(uri).buildUpon().clearQuery()

    findNavController().navigate(uriBuilder.build(), navOptions = buildNavOptions())
}

private fun buildNavOptions(): NavOptions = NavOptions
    .Builder()
    .setEnterAnim(R.anim.slide_in_right)
    .setExitAnim(R.anim.do_nothing)
    .setPopExitAnim(R.anim.slide_out_right)
    .setPopEnterAnim(R.anim.do_nothing)
    .build()
