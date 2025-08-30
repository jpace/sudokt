package org.incava.sudokt.rules

import org.incava.io.Qlog
import org.incava.sudokt.Cell
import org.incava.sudokt.Cells
import org.incava.sudokt.Rule
import org.incava.sudokt.Util

class RuleTwoPairs(cells: Cells) : Rule(cells) {
    constructor(cells: List<Cell>) : this(Cells(cells))

    fun checkRow(row: Int) {
        (0..7).forEach { col ->
            val id = Util.rowColumnToId(row, col)
            Qlog.info("id", id)
            val cell = cells.at(id)
            Qlog.info("cell", cell)
            (col + 1..8).forEach { otherCol ->
                val otherId = Util.rowColumnToId(row, otherCol)
                Qlog.info("otherId", otherId)
                val otherCell = cells.at(otherId)
                Qlog.info("otherCell", otherCell)
                if (cell.possible == otherCell.possible) {
                    Qlog.info("otherCell!", otherCell)
                    (0..8)
                        .map { cells.at(row, it) }
                        .filter { it.id != id && it.id != otherId }
                        .forEach { other ->
                            cell.possible.forEach {
                                Qlog.info(
                                    "other",
                                    other
                                ); other.removePossible(it)
                            }
                        }
                }
            }
            println()
        }
    }

    fun checkCellsInRow(row: Int, x: Cell, y: Cell) {
        if (x.possible == y.possible) {
            Qlog.info("x", x)
            Qlog.info("y", y)
            (0..8)
                .map { cells.at(row, it) }
                .filter { it.id != x.id && it.id != y.id }
                .forEach { other -> x.possible.forEach { Qlog.info("other", other); other.removePossible(it) } }
        }
    }

    fun description() = """
        for cells X and Y in the same unit U with possibilities I and J, 
        remove I and J a possible in all other cells in all of X's and Y's units"
    """.trimIndent()

    fun level() = 4
    override fun run() {
        TODO("Not yet implemented")
    }
}