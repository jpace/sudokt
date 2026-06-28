package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.impl.PuzzleData

// level 1 rule
class RuleCellInferPossible(cell: Cell) : RuleCell(cell) {
    val level = LEVEL

    override fun description() = """
        for a cell X without a defined number, set the possible values to 1 through 9"
    """.trimIndent()

    fun description(cell: Cell) = """
        for cell ${cell.position} without a defined number, set the possible values to 1 through 9"
    """.trimIndent()

    override fun run(): List<Cell> {
        if (cell.number == null) {
            if (cell.setPossibles(PuzzleData.numbers)) {
                setUpdated(cell, description(cell))
            }
            return listOf(cell)
        } else {
            return emptyList()
        }
    }

    companion object {
        const val LEVEL = 1
    }
}