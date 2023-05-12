package com.zeroillusion.minesweeperlite.domain.model

enum class GameMode(val row: Int, val column: Int, val mineCount: Int) {
    Easy(9, 9, 10),
    Medium(16, 16, 40),
    Hard(16, 30, 99)
}