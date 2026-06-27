package org.incava.sudokt.view

import org.incava.sudokt.Cell
import org.incava.sudokt.Puzzle

class PuzzleView1Line(puzzle: Puzzle, showId: Boolean, showNumber: Boolean, showPossible: Boolean) :
    PuzzleView(puzzle, showId, showNumber, showPossible) {

    override fun show(highlight: List<Cell>) {
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
}
