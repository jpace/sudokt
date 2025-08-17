package org.incava.sudokt

class Puzzle {
    val cells: List<Cell> = (0 until 81).map { Cell(it) }

    fun show() {
        val strings = (0 until 9).map { it.toString() }
        printRow("", "", strings)
        (0..8).forEach { row ->
            if (row % 3 == 0) {
                printBreak("", '=')
            } else {
                printBreak("", '-')
            }
            printCells(row, "id") { it.id }
            printCells(row, "number") { it.number() ?: "" }
            printCells(row, "possible") { it.possible.joinToString(" ") }
        }
        printBreak("", '=')
    }

    fun printBreak(row: Any, char: Char) {
        val str = char.toString().repeat(64)
        val strings = (0 until 9).map { str }
        printRow(row, "", strings)
    }

    fun printRow(row: Any, message: String, strings: List<String>) {
        System.out.printf(" %3.3s %-12.12s", row, message)
        val formatted = strings.map { String.format("%-20.20s", it) }
        val boxed = listOf(0, 3, 6).joinToString(" || ") { formatted.subList(it, it + 3).joinToString(" | ") }
        println(" || $boxed ||")
    }

    fun printCells(row: Int, message: String, supplier: (Cell) -> Any?) {
        val strings = (0..8).map { col ->
            val id = row * 9 + col
            val cell = cells[id]
            supplier(cell).toString()
        }
        printRow(row, message, strings)
    }
}
