package com.zeroillusion.minesweeperlite.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.zeroillusion.minesweeperlite.R
import com.zeroillusion.minesweeperlite.domain.model.GameState
import com.zeroillusion.minesweeperlite.presentation.GameViewModel

@Composable
fun FinishGameDialog(
    onDismiss: () -> Unit,
    viewModel: GameViewModel = hiltViewModel()
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.large
        ) {
            val currentGameState = viewModel.currentGameState.value
            Column(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(25.dp)
                    )
                    .border(
                        width = 4.dp,
                        color = MaterialTheme.colorScheme.onSecondary,
                        shape = RoundedCornerShape(25.dp)
                    )
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text =
                    if (currentGameState == GameState.Win) "You Win!" else if (currentGameState == GameState.Lose) "You Lose" else "",
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 16.dp),
                    style = TextStyle(
                        fontSize = 24.sp
                    )
                )
                Button(onClick = {
                    viewModel.updateBoard()
                }) {
                    Text(text = "Try again")
                }
            }
        }
    }
}