package com.zeroillusion.minesweeperlite.presentation.components

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zeroillusion.minesweeperlite.R
import com.zeroillusion.minesweeperlite.domain.model.Cell
import com.zeroillusion.minesweeperlite.domain.model.GameEvent
import com.zeroillusion.minesweeperlite.domain.model.GameState
import com.zeroillusion.minesweeperlite.presentation.GameViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/*
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    viewModel: GameViewModel = hiltViewModel()
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        var showFinishGameDialog by remember { mutableStateOf(false) }
        val currentGameState by remember { mutableStateOf(viewModel.currentGameState) }
        when (currentGameState.value) {
            GameState.Win -> {
                showFinishGameDialog = true
            }

            GameState.Init -> {
                showFinishGameDialog = false
                Toast.makeText(LocalContext.current, "Init", Toast.LENGTH_SHORT).show()
            }

            GameState.Playing -> {
            }

            GameState.Lose -> {
                showFinishGameDialog = true
            }
        }
        if (showFinishGameDialog) {
            FinishGameDialog(
                onDismiss = {
                    viewModel.updateBoard()
                }
            )
        }

        val board = viewModel.board
        val haptic = LocalHapticFeedback.current

        Column(Modifier.verticalScroll(rememberScrollState())) {
            Row(Modifier.horizontalScroll(rememberScrollState())) {
                Column {
                    for (i in 0 until viewModel.currentGameMode.value.row) {
                        Row {
                            for (j in 0 until viewModel.currentGameMode.value.column) {

                                val currentItem by remember(board) { mutableStateOf(board[i][j]) }

                                Image(
                                    painter = painterResource(
                                        id =
                                        if (currentGameState.value == GameState.Init) {
                                            R.drawable.ic_field
                                        } else {
                                            if (currentItem.isOpened
                                                && currentItem.isMine
                                            ) {
                                                R.drawable.ic_num_9
                                            } else if (!currentItem.isOpened
                                                && currentItem.isFlagged
                                            ) {
                                                R.drawable.ic_flag
                                            } else if (!currentItem.isOpened
                                                && !currentItem.isFlagged
                                            ) {
                                                R.drawable.ic_field
                                            } else if (currentItem.isOpened) {
                                                when (currentItem.minesNearby) {
                                                    0 -> R.drawable.ic_num_0
                                                    1 -> R.drawable.ic_num_1
                                                    2 -> R.drawable.ic_num_2
                                                    3 -> R.drawable.ic_num_3
                                                    4 -> R.drawable.ic_num_4
                                                    5 -> R.drawable.ic_num_5
                                                    6 -> R.drawable.ic_num_6
                                                    7 -> R.drawable.ic_num_7
                                                    8 -> R.drawable.ic_num_8
                                                    else -> R.drawable.ic_field
                                                }
                                            } else R.drawable.ic_field
                                        }
                                    ),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(40.dp)
                                        .padding(2.dp)
                                        .combinedClickable(
                                            onClick = {
                                                viewModel.updateBoard(
                                                    board[i][j],
                                                    GameEvent.OnClick
                                                )
                                            },
                                            onLongClick = {
                                                viewModel.updateBoard(
                                                    board[i][j],
                                                    GameEvent.OnLongClick
                                                )
                                                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                            },
                                            onDoubleClick = {
                                                viewModel.updateBoard(
                                                    board[i][j],
                                                    GameEvent.OnDoubleClick
                                                )
                                            }
                                        )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    viewModel: GameViewModel = hiltViewModel()
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        var showFinishGameDialog by remember { mutableStateOf(false) }
        val currentGameState by remember { mutableStateOf(viewModel.currentGameState) }
        when (currentGameState.value) {
            GameState.Win -> {
                showFinishGameDialog = true
            }

            GameState.Init -> {
                showFinishGameDialog = false
                Toast.makeText(LocalContext.current, "Init", Toast.LENGTH_SHORT).show()
            }

            GameState.Playing -> {
            }

            GameState.Lose -> {
                showFinishGameDialog = true
            }
        }
        if (showFinishGameDialog) {
            FinishGameDialog(
                onDismiss = {
                    viewModel.updateBoard()
                }
            )
        }

        val board = viewModel.board

        val m = viewModel.currentGameMode.value.row
        val n = viewModel.currentGameMode.value.column
        val state = remember(board) {
            MutableStateFlow(
                List(m) { i ->
                    List(n) { j ->
                        Cell(i, j)
                    }
                }
            )
        }

        val coroutineScope = rememberCoroutineScope()

        Row(Modifier.horizontalScroll(rememberScrollState())) {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                for (i in 0 until m)
                    Row {
                        for (j in 0 until n)
                            Cell(
                                value = remember(board) {
                                    state.map { it[i][j] }.stateIn(
                                        coroutineScope,
                                        SharingStarted.Eagerly,
                                        state.value[i][j]
                                    )
                                },
                                onClick = {
                                    state.value = state.value.toMutableList().also { column ->
                                        column[i] = column[i].toMutableList().also { row ->
                                            row[j] = row[j].copy(
                                                //counter = row[j].counter + 1
                                                //isOpened = !row[j].isOpened
                                                isOpened = true
                                            )
                                        }.toImmutableList()
                                    }.toImmutableList()
                                },
                                onLongClick = {
                                    state.value = state.value.toMutableList().also { column ->
                                        column[i] = column[i].toMutableList().also { row ->
                                            row[j] = row[j].copy(
                                                //counter = row[j].counter + 1
                                                isFlagged = !row[j].isFlagged
                                            )
                                        }.toImmutableList()
                                    }.toImmutableList()
                                },
                                viewModel = viewModel
                            )
                    }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Cell(
    value: StateFlow<Cell>,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    viewModel: GameViewModel
) {
    /*
    Text(
        value.collectAsState().value.toString(),
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(10.dp)
    )
     */
    val currentGameState by remember { mutableStateOf(viewModel.currentGameState) }
    val board = viewModel.board
    val haptic = LocalHapticFeedback.current

    val currentItem by value.collectAsState()

    Image(
        painter = painterResource(
            id =
            if (currentGameState.value == GameState.Init) {
                R.drawable.ic_field
            } else {
                if (currentItem.isOpened
                    && currentItem.isMine
                ) {
                    R.drawable.ic_num_9
                } else if (!currentItem.isOpened
                    && currentItem.isFlagged
                ) {
                    R.drawable.ic_flag
                } else if (!currentItem.isOpened
                    && !currentItem.isFlagged
                ) {
                    R.drawable.ic_field
                } else {
                    when (currentItem.minesNearby) {
                        0 -> R.drawable.ic_num_0
                        1 -> R.drawable.ic_num_1
                        2 -> R.drawable.ic_num_2
                        3 -> R.drawable.ic_num_3
                        4 -> R.drawable.ic_num_4
                        5 -> R.drawable.ic_num_5
                        6 -> R.drawable.ic_num_6
                        7 -> R.drawable.ic_num_7
                        8 -> R.drawable.ic_num_8
                        else -> R.drawable.ic_field
                    }
                }
            }
        ),
        contentDescription = null,
        modifier = Modifier
            .size(40.dp)
            .padding(2.dp)
            .combinedClickable(
                onClick = {
                    onClick()
                    viewModel.updateBoard(
                        board[value.value.x][value.value.y],
                        GameEvent.OnClick
                    )
                },
                onLongClick = {
                    onLongClick()
                    viewModel.updateBoard(
                        board[value.value.x][value.value.y],
                        GameEvent.OnLongClick
                    )
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                },
                onDoubleClick = {
                    onClick()
                    viewModel.updateBoard(
                        board[value.value.x][value.value.y],
                        GameEvent.OnDoubleClick
                    )
                }
            )
    )
}