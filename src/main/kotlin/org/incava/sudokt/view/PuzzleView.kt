package org.incava.sudokt.view

import org.incava.sudokt.Cell
import org.incava.sudokt.Puzzle
import org.incava.sudokt.Util

class PuzzleView(val puzzle: Puzzle, val showId: Boolean, val showNumber: Boolean, val showPossible: Boolean) {
    val cells = puzzle.cells
    private val numRows = 9
    private val numColumns = 9
    private val rowWidth = if (showPossible) 12 else 8

    fun show() {
        val header = (0 until numColumns).map { it.toString() }
        printRow("", "", header)
        (0 until numRows).forEach { row ->
            if (row % 3 == 0) {
                printBreak("", '=')
            } else {
                printBreak("", '-')
            }
            if (showId) {
                printCells(row, "id") { it.id }
            }
            if (showNumber) {
                printCells(row, "number") { it.number() ?: "" }
            }
            if (showPossible) {
                printCells(row, "possible") { formatPossible(it) }
            }
        }
        printBreak("", '=')
        println()
    }

    fun formatPossible(cell: Cell): String {
        return if (cell.possible.size == 9) {
            "1..9"
        } else {
            cell.possible.joinToString(" ")
        }
    }

    fun printBreak(row: Any, char: Char) {
        val str = char.toString().repeat(64)
        val strings = (0 until numColumns).map { str }
        printRow(row, "", strings)
    }

    fun printRow(row: Any, message: String, strings: List<String>) {
        System.out.printf(" %3.3s %-12.12s", row, message)
        val formatted = strings.map { String.format("%-$rowWidth.${rowWidth}s", it) }
        val boxed = listOf(0, 3, 6).joinToString(" || ") { formatted.subList(it, it + 3).joinToString(" | ") }
        println(" || $boxed ||")
    }

    fun printCells(row: Int, message: String, supplier: (Cell) -> Any?) {
        val strings = (0 until numColumns).map { col ->
            val id = Util.rowColumnToId(row, col)
            val cell = cells[id]
            supplier(cell).toString()
        }
        printRow(row, message, strings)
    }
}
