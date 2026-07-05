package org.incava.sudokt.view

import org.incava.sudokt.Cell
import org.incava.sudokt.Puzzle
import java.io.PrintStream

class PuzzleView1Line(puzzle: Puzzle, showId: Boolean, showNumber: Boolean, showPossible: Boolean, out: PrintStream = System.out) :
    PuzzleView(puzzle, showId, showNumber, showPossible, out) {

    override fun show(highlight: List<Cell>) {
        // out.print("\u001b[H\u001b[2J")
        (0 until numRows).forEach { row ->
            if (row % 3 == 0) {
                printBreak("", '=')
            } else {
                printBreak("", '-')
            }
            if (showId) {
                printCells(highlight, row, "id") { it.id }
            }
            printCells(highlight, row, "") {
                if (it.number == null) {
                    formatPossible(it)
                } else {
                    it.number
                }
            }
        }
        printBreak("", '=')
        println()
    }

    override fun printRow(row: Any, message: String, strings: List<Pair<String, Boolean>>) {
        printRow(null, strings)
    }
}
