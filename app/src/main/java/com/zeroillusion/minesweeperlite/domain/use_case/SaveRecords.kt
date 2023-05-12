package com.zeroillusion.minesweeperlite.domain.use_case

import com.zeroillusion.minesweeperlite.domain.repository.MinesweeperLiteRepository

class SaveRecords(
    private val repository: MinesweeperLiteRepository
) {
    operator fun invoke(records: Map<String, Int>) {
        return repository.saveRecords(records)
    }
}