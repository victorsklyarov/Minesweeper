package com.zeroillusion.minesweeperlite.domain.use_case

import com.zeroillusion.minesweeperlite.domain.model.GameState
import com.zeroillusion.minesweeperlite.domain.repository.MinesweeperLiteRepository

class GetGameState(
    private val repository: MinesweeperLiteRepository
) {
    operator fun invoke(): GameState {
        return repository.getGameState()
    }
}