package com.verumomnis.forensic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.verumomnis.forensic.ui.VerumTheme
import com.verumomnis.forensic.ui.navigation.VerumNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            VerumTheme {
                val nav = rememberNavController()
                VerumNavGraph(nav)
            }
        }
    }
}
