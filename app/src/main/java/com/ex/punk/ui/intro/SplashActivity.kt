package com.ex.punk.ui.intro

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ex.punk.R
import com.ex.punk.ui.main.MainActivity
import com.ex.punk.ui.theme.BrewdogPunkTheme
import com.ex.punk.ui.theme.IntroBackground
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : ComponentActivity() {
    companion object{
        private const val DELAY = 2000L
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BrewdogPunkTheme {

                Intro()
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                delay(DELAY)
                startApp()
            }
        }
    }

    private fun startApp(){
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
@Composable
fun Intro() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = IntroBackground
    ) {
        Column(modifier = Modifier.fillMaxSize()){
            Spacer(modifier = Modifier.height(80.dp))
            Text(
                text = stringResource(id = R.string.splash_00),
                textAlign = TextAlign.Center,
                color = Color.White,
                letterSpacing = 2.sp,
                fontSize = 14.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
