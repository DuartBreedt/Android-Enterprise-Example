package com.duartbreedt.androidtemplate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.duartbreedt.androidtemplate.theme.AndroidTemplateTheme

abstract class ComposeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ComposeFragmentWrapper()
            }
        }
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