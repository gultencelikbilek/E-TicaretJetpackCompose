package com.example.e_ticaret

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.e_ticaret.navigation.AppBottomNav
import com.example.e_ticaret.navigation.AppNavigation
import com.example.e_ticaret.navigation.BottomNav
import com.example.e_ticaret.presentation.product_screen.ProductScreen
import com.example.e_ticaret.ui.theme.ETicaretTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        // Splash screen'i belirli bir süre göstermek için
        setContent {
            var showSplashScreen by remember { mutableStateOf(true) }
            val lifecycleOwner = LocalLifecycleOwner.current

            LaunchedEffect(key1 = lifecycleOwner) {
                lifecycleOwner.lifecycleScope.launch {
                    delay(3000) // 3000 ms = 3 saniye
                    showSplashScreen = false
                }
            }

            if (showSplashScreen) {
                SplashContent()
            } else {
                ETicaretTheme {
                    val navController = rememberNavController()
                    Scaffold(
                        bottomBar = {
                            AppBottomNav(navController = navController)
                        }
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            AppNavigation(navController)
                        }
                    }

                }
            }
        }
    }
}


@Composable
fun SplashContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Splash Screen içeriğiniz (örneğin bir logo)
        Image(
            painter = painterResource(id = R.drawable.shopping),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )
    }
}