package com.zeroillusion.minesweeperlite.data.repository

import android.app.Application
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.zeroillusion.minesweeperlite.domain.repository.MinesweeperLiteRepository
import com.zeroillusion.minesweeperlite.domain.model.Cell
import com.zeroillusion.minesweeperlite.domain.model.GameEvent
import com.zeroillusion.minesweeperlite.domain.model.GameMode
import com.zeroillusion.minesweeperlite.domain.model.GameState
import kotlin.math.max
import kotlin.math.min

class MinesweeperLiteRepositoryImpl(
    app: Application
) : MinesweeperLiteRepository {

    private var pref: SharedPreferences
    private var gameMode: GameMode
    private var gameState: GameState
    private var minesLeftCount = 0

    init {
        val masterKeyAlias = MasterKey.Builder(app, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        pref = EncryptedSharedPreferences.create(
            app,
            "ScoreKey",
            masterKeyAlias,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
        gameMode = GameMode.Easy
        gameState = GameState.Init
    }

    override fun updateBoard(board: Array<Array<Cell>>, cell: Cell?, gameEvent: GameEvent?): Array<Array<Cell>> {

        if (cell == null || gameEvent == null) {
            gameState = GameState.Init
            minesLeftCount = 0
            return Array(gameMode.row) { i -> Array(gameMode.column) { j -> Cell(i, j) } }
        }

        if (gameState == GameState.Init) {
            if (gameEvent == GameEvent.OnClick) {
                fillBoard(board, gameMode.mineCount, cell)
                setMinesNearby(board)
                openCell(cell, board)
                //START CHRONOMETER
                gameState = GameState.Playing
            }
        } else if (gameState == GameState.Playing) {
            when (gameEvent) {
                GameEvent.OnClick -> {
                    gameState = onClick(board, cell, gameMode)
                }

                GameEvent.OnDoubleClick -> {
                    gameState = if (cell.isOpened) {
                        chording(board, cell)
                        checkGameState(board, gameMode.mineCount)
                    } else {
                        onClick(board, cell, gameMode)
                    }
                }

                GameEvent.OnLongClick -> {
                    if (!cell.isOpened) {
                        toggleFlag(board, cell)
                    }
                }
            }
        }
        return board
    }

    override fun saveRecords(records: Map<String, Int>) {
        records.forEach { (key, value) ->
            pref.edit().putInt(key, value).apply()
        }
    }

    override fun getRecords(): Map<String, Int> {
        return mapOf(
            "BEST_EASY_SCORE" to pref.getInt("BEST_EASY_SCORE", 999),
            "BEST_MEDIUM_SCORE" to pref.getInt("BEST_MEDIUM_SCORE", 999),
            "BEST_HARD_SCORE" to pref.getInt("BEST_HARD_SCORE", 999)
        )
    }

    override fun setGameMode(gameMode: GameMode) {
        this.gameMode = gameMode
    }

    override fun getGameMode(): GameMode {
        return gameMode
    }

    override fun getGameState(): GameState {
        return gameState
    }


    private fun onClick(board: Array<Array<Cell>>, cell: Cell, gameMode: GameMode): GameState {
        if (!cell.isFlagged) {
            openCell(cell, board)
            return checkGameState(board, gameMode.mineCount)
        }
        return GameState.Playing
    }

    private fun fillBoard(board: Array<Array<Cell>>, mineCount: Int, cell: Cell): Array<Array<Cell>> {
        val cells = board.flatten().toMutableList()
        for (i in max(cell.x - 1, 0) until min(cell.x + 2, board.size)) {
            for (j in max(cell.y - 1, 0) until min(cell.y + 2, board[i].size)) {
                cells.remove(board[i][j])
            }
        }
        cells.shuffle()
        cells.take(mineCount).forEach { it.isMine = true }
        return board
    }

    private fun setMinesNearby(board: Array<Array<Cell>>) {
        for (i in board.indices) {
            for (j in board[i].indices) {
                if (!board[i][j].isMine) {
                    board[i][j].minesNearby = getMinesNearby(board, i, j)
                }
            }
        }
    }

    private fun getMinesNearby(board: Array<Array<Cell>>, x: Int, y: Int): Int {
        var count = 0
        for (i in max(x - 1, 0) until min(x + 2, board.size)) {
            for (j in max(y - 1, 0) until min(y + 2, board[i].size)) {
                if (board[i][j].isMine) {
                    count++
                }
            }
        }
        return count
    }

    private fun openCell(cell: Cell, board: Array<Array<Cell>>) {
        cell.isOpened = true
        if (cell.minesNearby == 0 && !cell.isMine) {
            for (i in max(cell.x - 1, 0) until min(cell.x + 2, board.size)) {
                for (j in max(cell.y - 1, 0) until min(cell.y + 2, board[i].size)) {
                    if (!board[i][j].isOpened && !board[i][j].isFlagged) {
                        openCell(board[i][j], board)
                    }
                }
            }
        }
    }

    private fun toggleFlag(board: Array<Array<Cell>>, cell: Cell): Array<Array<Cell>> {
        if (cell.isFlagged) minesLeftCount-- else minesLeftCount++
        board[cell.x][cell.y].isFlagged = !cell.isFlagged
        return board
    }

    private fun chording(board: Array<Array<Cell>>, cell: Cell): Array<Array<Cell>> {
        if (cell.isOpened && (cell.minesNearby == getFlagsNearby(board, cell.x, cell.y))) {
            for (i in max(cell.x - 1, 0) until min(cell.x + 2, board.size)) {
                for (j in max(cell.y - 1, 0) until min(cell.y + 2, board[i].size)) {
                    if (!board[i][j].isOpened && !board[i][j].isFlagged) {
                        openCell(board[i][j], board)
                    }
                }
            }
        }
        return board
    }

    private fun getFlagsNearby(board: Array<Array<Cell>>, x: Int, y: Int): Int {
        var count = 0
        for (i in max(x - 1, 0) until min(x + 2, board.size)) {
            for (j in max(y - 1, 0) until min(y + 2, board[i].size)) {
                if (board[i][j].isFlagged) {
                    count++
                }
            }
        }
        return count
    }

    private fun checkGameState(board: Array<Array<Cell>>, mineCount: Int): GameState {
        var count = 0
        for (i in board.indices) {
            for (j in board[i].indices) {
                if (board[i][j].isOpened && !board[i][j].isMine) {
                    count++
                }
                if (board[i][j].isOpened && board[i][j].isMine) {
                    return GameState.Lose
                }
            }
        }
        if (count == board.size * board[0].size - mineCount) {
            return GameState.Win
        }
        return GameState.Playing
    }

    /*
    private fun getTime(): Flow<String> = flow {
        repeat(1000) { i ->
            emit("%03d".format(i))
            delay(1000L)
        }
    }

    private fun getMinesLeftCount(): Int {
        return minesLeftCount
    }
    */
}