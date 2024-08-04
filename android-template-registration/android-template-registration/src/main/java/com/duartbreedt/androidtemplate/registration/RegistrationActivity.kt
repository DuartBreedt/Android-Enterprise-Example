package com.duartbreedt.androidtemplate.registration

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.duartbreedt.androidtemplate.registration.databinding.ActivityRegistrationBinding

class RegistrationActivity : FragmentActivity() {

    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}