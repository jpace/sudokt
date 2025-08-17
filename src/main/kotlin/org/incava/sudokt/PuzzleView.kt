package org.incava.sudokt

class PuzzleView(val puzzle: Puzzle, val showId: Boolean, val showNumber: Boolean, val showPossible: Boolean) {
    val cells = puzzle.cells

    fun show() {
        val strings = (0 until 9).map { it.toString() }
        printRow("", "", strings)
        (0..8).forEach { row ->
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
                printCells(row, "possible") { it.possible.joinToString(" ") }
            }
        }
        printBreak("", '=')
        println()
    }

    fun printBreak(row: Any, char: Char) {
        val str = char.toString().repeat(64)
        val strings = (0 until 9).map { str }
        printRow(row, "", strings)
    }

    fun printRow(row: Any, message: String, strings: List<String>) {
        System.out.printf(" %3.3s %-12.12s", row, message)
        val width = if (showPossible) 20 else 8
        val formatted = strings.map { String.format("%-$width.${width}s", it) }
        val boxed = listOf(0, 3, 6).joinToString(" || ") { formatted.subList(it, it + 3).joinToString(" | ") }
        println(" || $boxed ||")
    }

    fun printCells(row: Int, message: String, supplier: (Cell) -> Any?) {
        val strings = (0..8).map { col ->
            val id = rowColumnToId(row, col)
            val cell = cells[id]
            supplier(cell).toString()
        }
        printRow(row, message, strings)
    }

    fun rowColumnToId(row: Int, column: Int) = row * 9 + column
}
