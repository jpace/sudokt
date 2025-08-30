package org.incava.sudokt.test

import org.incava.sudokt.Puzzle

object TestFixture {
    val puzzle1: Puzzle

    init {
        puzzle1 = Puzzle()
        puzzle1.let {
            it.setCells(0, mapOf(1 to 8, 2 to 1, 5 to 6, 6 to 7, 8 to 5))
            it.setCells(1, mapOf(0 to 5, 1 to 6, 2 to 3, 3 to 8, 7 to 2))
            it.setCells(2, mapOf(3 to 3, 4 to 9, 5 to 5, 6 to 8))

            it.setCells(3, listOf(null, null, 5, null, 6, 7, 3, 1, null))
            it.setCells(4, listOf(null, 9, 8, null, null, null, 6, null, null))
            it.setCells(5, listOf(null, null, null, 5, 1, 8))
            it.setCells(6, listOf(8, null, 6, null, 2, null, 5, 9, 3))
            it.setCells(7, listOf(3, null, null, 6, 8, null, null, null, 2))
            it.setCells(8, listOf(2, 7, null, 1))
        }
    }
}