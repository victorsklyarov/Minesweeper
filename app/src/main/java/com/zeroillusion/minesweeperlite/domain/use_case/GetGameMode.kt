package com.zeroillusion.minesweeperlite.domain.use_case

import com.zeroillusion.minesweeperlite.domain.model.GameMode
import com.zeroillusion.minesweeperlite.domain.repository.MinesweeperLiteRepository

class GetGameMode(
    private val repository: MinesweeperLiteRepository
) {
    operator fun invoke(): GameMode {
        return repository.getGameMode()
    }
}