package com.zeroillusion.minesweeperlite.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.zeroillusion.minesweeperlite.domain.model.Cell
import com.zeroillusion.minesweeperlite.domain.model.GameEvent
import com.zeroillusion.minesweeperlite.domain.model.GameMode
import com.zeroillusion.minesweeperlite.domain.model.GameState
import com.zeroillusion.minesweeperlite.domain.use_case.GetGameMode
import com.zeroillusion.minesweeperlite.domain.use_case.GetGameState
import com.zeroillusion.minesweeperlite.domain.use_case.GetRecords
import com.zeroillusion.minesweeperlite.domain.use_case.SaveRecords
import com.zeroillusion.minesweeperlite.domain.use_case.SetGameMode
import com.zeroillusion.minesweeperlite.domain.use_case.UpdateBoard
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val updateBoard: UpdateBoard,
    private val setGameMode: SetGameMode,
    private val getGameMode: GetGameMode,
    private val getGameState: GetGameState,
    private val saveRecords: SaveRecords,
    private val getRecords: GetRecords
) : ViewModel() {

    //private val _board = mutableStateOf<Array<Array<Cell>>>(arrayOf())
    //val board: State<Array<Array<Cell>>> = _board

    //var boardState by mutableStateOf<Array<Array<MutableState<Cell>>>>(arrayOf())

    var board by mutableStateOf<Array<Array<Cell>>>(arrayOf())//, neverEqualPolicy())

    private val _currentGameMode = mutableStateOf(GameMode.Easy)
    val currentGameMode: State<GameMode> = _currentGameMode

    private val _currentGameState = mutableStateOf(GameState.Init)
    val currentGameState: State<GameState> = _currentGameState

    init {
        updateBoard()
    }

    fun updateBoard(cell: Cell? = null, gameEvent: GameEvent? = null) {
        //val newBoard = boardState.map { row -> row.map { item -> item.value }.toTypedArray() }.toTypedArray()
        //_board.value = updateBoard(newBoard, cell, gameEvent)
        //boardState = board.value.map { row -> row.map { item -> mutableStateOf(item) }.toTypedArray() }.toTypedArray()

        board = updateBoard(board, cell, gameEvent)

        _currentGameState.value = getGameState()
    }

    fun changeGameMode(gameMode: GameMode) {
        setGameMode(gameMode)
        _currentGameMode.value = getGameMode()
        updateBoard()
    }
}