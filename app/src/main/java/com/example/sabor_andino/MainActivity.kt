package com.example.sabor_andino

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.sabor_andino.navigation.AppNavigation
import com.example.sabor_andino.ui.theme.SaborAndinoTheme

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