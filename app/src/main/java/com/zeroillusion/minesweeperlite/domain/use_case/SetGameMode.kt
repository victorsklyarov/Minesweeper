package com.zeroillusion.minesweeperlite.domain.use_case

import com.zeroillusion.minesweeperlite.domain.model.GameMode
import com.zeroillusion.minesweeperlite.domain.repository.MinesweeperLiteRepository

class SetGameMode(
    private val repository: MinesweeperLiteRepository
) {
    operator fun invoke(gameMode: GameMode) {
        return repository.setGameMode(gameMode)
    }
}