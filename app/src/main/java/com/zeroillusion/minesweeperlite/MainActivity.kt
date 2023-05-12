package com.zeroillusion.minesweeperlite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.zeroillusion.minesweeperlite.presentation.components.GameScreen
import com.zeroillusion.minesweeperlite.presentation.components.TopBar
import com.zeroillusion.minesweeperlite.ui.theme.MinesweeperLiteTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MinesweeperLiteTheme {
                Scaffold(
                    topBar = { TopBar() },
                    content = { innerPadding ->
                        GameScreen(
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                )
            }
        }
    }
}