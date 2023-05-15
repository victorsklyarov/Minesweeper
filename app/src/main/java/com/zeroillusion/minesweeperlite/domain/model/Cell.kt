package com.zeroillusion.minesweeperlite.domain.model

data class Cell(
    val x: Int,
    val y: Int,
    val isMine: Boolean = false,
    val isFlagged: Boolean = false,
    val isOpened: Boolean = false,
    val minesNearby: Int = 0
)