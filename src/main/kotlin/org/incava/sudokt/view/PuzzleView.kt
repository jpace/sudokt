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
        show(emptyList())
    }

    fun show(highlight: List<Cell>) {
        val header = (0 until numColumns).map { it.toString() to false }
        printRow("", "", header)
        (0 until numRows).forEach { row ->
            if (row % 3 == 0) {
                printBreak("", '=')
            } else {
                printBreak("", '-')
            }
            if (showId) {
                printCells(highlight, row, "id") { it.id }
            }
            if (showNumber) {
                printCells(highlight, row, "number") { it.number() ?: "" }
            }
            if (showPossible) {
                printCells(highlight, row, "possible") { formatPossible(it) }
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
        val strings = (0 until numColumns).map { str to false }
        printRow(row, "", strings)
    }

    fun printRow(row: Any, message: String, strings: List<Pair<String, Boolean>>) {
        System.out.printf(" %3.3s %-12.12s", row, message)
        val formatted = strings.map { (str, highlight) ->
            if (highlight) {
                val width = rowWidth - 4
                String.format(">  %-$width.${width}s  <", str)
            } else {
                String.format(" %-$rowWidth.${rowWidth}s ", str)
            }
        }
        val boxed = listOf(0, 3, 6).joinToString("||") { formatted.subList(it, it + 3).joinToString("|") }
        println("||$boxed||")
    }

    fun printCells(highlight: List<Cell>, row: Int, message: String, supplier: (Cell) -> Any?) {
        val strings = (0 until numColumns).map { col ->
            val id = Util.rowColumnToId(row, col)
            val cell = cells[id]
            supplier(cell).toString() to (highlight.isNotEmpty() && highlight.contains(cell))
        }
        printRow(row, message, strings)
    }
}
