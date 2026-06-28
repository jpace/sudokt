package org.incava.sudokt.rules

import org.incava.sudokt.Cell

class RuleNakedPair(x: Cell, y: Cell, unitCells: List<Cell>) : RuleCellPair(x, y, unitCells) {
    override fun description() = """
        for cells X and Y in the same unit U with two matching possibilities (number1, number2) that
        are not possibility in other cells, set the possibilities of X and Y to only (number1, number2)"
    """.trimIndent()

    fun description(matching: Set<Int>, other: Cell) = """
        Naked Pair
        for cells ${x.position} and ${y.position} in the same unit U with two identical possibilities $matching of size
        two, that, removing all of those possibilities from the other cell ${other.position} in the unit"
    """.trimIndent()

    override fun run(x: Cell, y: Cell, unitCells: List<Cell>): List<Cell> {
        if (x.possible.size != 2 && y.possible.size != 2) {
            return emptyList()
        } else if (x.possible == y.possible) {
            val updatedCells = mutableListOf<Cell>()
            unitCells.forEach { other ->
                if (other != x && other != y) {
                    if (other.removePossibles(x.possible)) {
                        setUpdated(other, description(x.possible, other))
                        updatedCells += other
                    }
                }
            }
            return updatedCells
        } else {
            return emptyList()
        }
    }
}
