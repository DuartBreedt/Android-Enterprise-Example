package com.duartbreedt.androidtemplate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.fragment.app.Fragment
import androidx.fragment.compose.content
import com.duartbreedt.androidtemplate.theme.AndroidTemplateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class ComposeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = content {
        ComposeFragmentWrapper()
    }

    @Composable
    protected fun ComposeFragmentWrapper() {
        AndroidTemplateTheme { Surface(modifier = Modifier.fillMaxSize()) { FragmentContent() } }
    }

    @Composable
    abstract fun FragmentContent()

    @Composable
    abstract fun PreviewFragmentContent()
}