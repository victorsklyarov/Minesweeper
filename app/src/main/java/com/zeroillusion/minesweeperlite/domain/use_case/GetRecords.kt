package com.zeroillusion.minesweeperlite.domain.use_case

import com.zeroillusion.minesweeperlite.domain.repository.MinesweeperLiteRepository

class GetRecords(
    private val repository: MinesweeperLiteRepository
) {
    operator fun invoke(): Map<String, Int> {
        return repository.getRecords()
    }
}