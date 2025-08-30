package org.incava.sudokt.rules

import org.incava.sudokt.Cells
import org.junit.Assert
import java.lang.reflect.Executable
import kotlin.test.assertEquals

class CellsAssertions(val cells: Cells) {
    fun assertPossible(expected: Set<Int>, row: Int, column: Int) {
        assertEquals(expected, cells.at(row, column).possible)
    }

    fun assertPossible(expected: Set<Int>, position: Pair<Int, Int>) {
        assertEquals(expected, cells.at(position.first, position.second).possible)
    }

    fun cellToThis(block: (CellsAssertions) -> Unit): () -> Unit {
        val function: () -> Unit = { block(this) }
        return function
    }

    fun assertAll(vararg executables: (CellsAssertions) -> Unit) {
        val withThis = executables.toList().map { { it(this) } }
        org.junit.jupiter.api.assertAll(withThis)
    }
}