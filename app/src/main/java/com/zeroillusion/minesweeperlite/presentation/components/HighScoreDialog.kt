package com.zeroillusion.minesweeperlite.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun HighScoreDialog(
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.large
        ) {
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
                    text = "Best score",
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 16.dp),
                    style = TextStyle(
                        fontSize = 24.sp
                    )
                )
                Row(
                    modifier = Modifier.widthIn(min = 160.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Easy game:")
                    Text(text = "999")
                }
                Row(
                    modifier = Modifier.widthIn(min = 160.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Medium game:")
                    Text(text = "999")
                }
                Row(
                    modifier = Modifier.widthIn(min = 160.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Hard game:")
                    Text(text = "999")
                }

                Button(onClick = onDismiss) {
                    Text(text = "Reset score")
                }
                Button(onClick = onDismiss) {
                    Text(text = "Close")
                }
            }
        }
    }
}