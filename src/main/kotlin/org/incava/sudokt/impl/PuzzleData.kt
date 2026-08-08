package org.incava.sudokt.impl

object PuzzleData {
    const val UNIT_SIZE = 9
    val cellIndices = (0 until UNIT_SIZE).toList()
    const val CELL_COUNT = UNIT_SIZE * UNIT_SIZE
    val numbers = (1..9).toSet()
}