package com.zeroillusion.minesweeperlite.domain.repository

import com.zeroillusion.minesweeperlite.domain.model.Cell
import com.zeroillusion.minesweeperlite.domain.model.GameEvent
import com.zeroillusion.minesweeperlite.domain.model.GameMode
import com.zeroillusion.minesweeperlite.domain.model.GameState

interface MinesweeperLiteRepository {
    fun updateBoard(board: Array<Array<Cell>>, cell: Cell?, gameEvent: GameEvent?): Array<Array<Cell>>
    fun saveRecords(records: Map<String, Int>)
    fun getRecords(): Map<String, Int>
    fun setGameMode(gameMode: GameMode)
    fun getGameMode(): GameMode
    fun getGameState(): GameState
}