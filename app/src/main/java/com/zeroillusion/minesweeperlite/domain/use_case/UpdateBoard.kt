package com.zeroillusion.minesweeperlite.domain.use_case

import com.zeroillusion.minesweeperlite.domain.model.Cell
import com.zeroillusion.minesweeperlite.domain.model.Coordinate
import com.zeroillusion.minesweeperlite.domain.model.GameEvent
import com.zeroillusion.minesweeperlite.domain.repository.MinesweeperLiteRepository

class UpdateBoard(
    private val repository: MinesweeperLiteRepository
) {
    operator fun invoke(board: Array<Array<Cell>>, cell: Cell?, gameEvent: GameEvent?, coordinate: Coordinate?): Array<Array<Cell>> {
        return repository.updateBoard(board, cell, gameEvent, coordinate)
    }
}