package com.duartbreedt.androidtemplate

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun PrimaryButton(text: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = text, color = MaterialTheme.colorScheme.onPrimary)
    }
}