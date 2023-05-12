package com.zeroillusion.minesweeperlite.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zeroillusion.minesweeperlite.domain.model.GameMode
import com.zeroillusion.minesweeperlite.presentation.GameViewModel

@Composable
fun TopBar(
    viewModel: GameViewModel = hiltViewModel()
) {
    var gameModeSelection by remember { mutableStateOf(false) }
    var expanded2 by remember { mutableStateOf(false) }
    var showHighScoreDialog by remember { mutableStateOf(false) }
    var showAboutDialog by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .height(64.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            viewModel.updateBoard()
        }) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = "Reload")
        }
        /*
        Row {
            Row {
                Icon(imageVector = Icons.Default.Settings, contentDescription = null)
                //Text(
                //    text = viewModel.currentGameMode.value.mines.toString()
                //)
            }
            Row {
                Icon(imageVector = Icons.Default.Settings, contentDescription = null)
                Text(
                    text = "%03d"//.format(viewModel.chronometer.value)
                )
            }
        }
         */
        Row {
            OutlinedButton(onClick = { gameModeSelection = true }) {
                Text(
                    text = viewModel.currentGameMode.value.name
                )
            }
            DropdownMenu(
                expanded = gameModeSelection,
                onDismissRequest = { gameModeSelection = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Easy") },
                    onClick = {
                        viewModel.changeGameMode(GameMode.Easy)
                        gameModeSelection = false
                    })
                Divider()
                DropdownMenuItem(
                    text = { Text("Medium") },
                    onClick = {
                        viewModel.changeGameMode(GameMode.Medium)
                        gameModeSelection = false
                    })
                Divider()
                DropdownMenuItem(
                    text = { Text("Hard") },
                    onClick = {
                        viewModel.changeGameMode(GameMode.Hard)
                        gameModeSelection = false
                    })
            }
            IconButton(onClick = { expanded2 = true }) {
                Icon(Icons.Default.MoreVert, contentDescription = "Localized description")
            }
            DropdownMenu(
                expanded = expanded2,
                onDismissRequest = { expanded2 = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Best score") },
                    onClick = {
                        showHighScoreDialog = true
                        expanded2 = false
                    })
                Divider()
                DropdownMenuItem(
                    text = { Text("About") },
                    onClick = {
                        showAboutDialog = true
                        expanded2 = false
                    })
            }
        }
    }
    if (showHighScoreDialog) {
        HighScoreDialog(
            onDismiss = {showHighScoreDialog = false}
        )
    }
    if (showAboutDialog) {
        AboutDialog(
            onDismiss = {showAboutDialog = false}
        )
    }
}