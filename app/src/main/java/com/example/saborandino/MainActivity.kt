package com.example.saborandino

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.saborandino.navigation.AppNavigation
import com.example.saborandino.ui.theme.SaborAndinoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SaborAndinoTheme {
                AppNavigation()
            }
        }
    }
}