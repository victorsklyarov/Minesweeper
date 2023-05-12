package com.zeroillusion.minesweeperlite.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.zeroillusion.minesweeperlite.BuildConfig
import com.zeroillusion.minesweeperlite.R


@Composable
fun AboutDialog(
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
                    text = stringResource(id = R.string.app_name),
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 16.dp),
                    style = TextStyle(
                        fontSize = 24.sp
                    )
                )
                Box(
                    modifier = Modifier
                        .height(75.dp)
                        .width(75.dp)
                        .background(
                            color = Color(0xFFFF005A),
                            shape = RoundedCornerShape(15.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = null
                    )
                }
                Text(
                    text = "Version",
                    modifier = Modifier.padding(vertical = 8.dp),
                    style = TextStyle(fontSize = 14.sp)
                )
                Text(
                    text = BuildConfig.VERSION_NAME,
                    modifier = Modifier.padding(bottom = 8.dp),
                    style = TextStyle(fontSize = 14.sp)
                )
                Text(
                    text = "©2023 Zero Illusion",
                    modifier = Modifier.padding(bottom = 8.dp),
                    style = TextStyle(fontSize = 14.sp)
                )
                Button(onClick = onDismiss) {
                    Text(text = "Close")
                }
            }
        }
    }
}