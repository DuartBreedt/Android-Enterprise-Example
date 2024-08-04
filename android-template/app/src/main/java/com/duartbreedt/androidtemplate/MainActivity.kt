package com.duartbreedt.androidtemplate

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import com.duartbreedt.androidtemplate.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    private lateinit var binding: ActivityMainBinding

    private val navController: NavController by lazy { getNavController(R.id.app_nav_host) }

    @Inject
    lateinit var appNavigationGraphModifiers: Set<@JvmSuppressWildcards AppNavigationGraphModifier>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appNavigationGraphModifiers.forEach { it.addActivity(navController) }
    }
}