package org.incava.sudokt.view

import org.incava.sudokt.Cell
import org.incava.sudokt.Puzzle
import org.incava.sudokt.Util

abstract class PuzzleView(val puzzle: Puzzle, val showId: Boolean, val showNumber: Boolean, val showPossible: Boolean) {
    val cells = puzzle.cells
    val numRows = 9
    val numColumns = 9
    val rowWidth = if (showPossible) 12 else 8

    fun show() {
        show(emptyList())
    }

    abstract fun show(highlight: List<Cell>)

    fun formatPossible(cell: Cell): String {
        return if (cell.possible.size == 9) {
            "[1..9]"
        } else {
            val possible = cell.possible
            if (possible.isEmpty()) {
                "?"
            } else {
                "[${cell.possible.joinToString(",")}]"
            }
        }
    }

    fun printBreak(row: Any, char: Char) {
        val str = char.toString().repeat(64)
        val strings = (0 until numColumns).map { str to false }
        printRow(row, "", strings)
    }

    open fun printRow(row: Any, message: String, strings: List<Pair<String, Boolean>>) {
        val msg = String.format(" %3.3s %-12.12s", row, message)
        printRow(msg, strings)
    }

    fun printRow(lhsMessage: String?, strings: List<Pair<String, Boolean>>) {
        if (lhsMessage != null) {
            System.out.printf("%s", lhsMessage)
        }
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
