package org.incava.sudokt.rules

import org.incava.io.Qlog
import org.incava.sudokt.Cells
import org.incava.sudokt.Puzzle
import org.incava.sudokt.test.TestFixture
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

open class RuleTestBase {
    lateinit var cells: Cells
    lateinit var puzzle: Puzzle
    val all = (1..9).toSet()
    val none = emptySet<Int>()

    @BeforeTest
    fun setup() {
        Qlog.info("running setup")
        puzzle = TestFixture.createPuzzle2()
        cells = Cells(puzzle.cells)
    }

    fun assertPossible(expected: Set<Int>, row: Int, column: Int) {
        assertEquals(expected, cells[row, column].possible)
    }

    fun assertPossible(expected: Set<Int>, position: Pair<Int, Int>) {
        assertEquals(expected, cells[position.first, position.second].possible)
    }
}