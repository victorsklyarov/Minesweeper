package com.zeroillusion.minesweeperlite.domain.model

data class Cell(
    val x: Int,
    val y: Int,
    var isMine: Boolean = false,
    var isFlagged: Boolean = false,
    var isOpened: Boolean = false,
    var minesNearby: Int = 0
)