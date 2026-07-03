package org.incava.sudokt.rules.generators

import org.incava.sudokt.Cell
import org.incava.sudokt.PuzzleCells

class UnitFunctions {
    val functions: Map<String, (PuzzleCells, Int) -> List<Cell>> = mapOf(
        "row" to PuzzleCells::inRow,
        "column" to PuzzleCells::inColumn,
        "box" to PuzzleCells::inBox
    )

    fun getRandom(): Pair<String, (PuzzleCells, Int) -> List<Cell>> {
        return functions.entries.random().toPair()
    }
}