package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.RuleResult

// level 1 rule
class RuleCellSinglePossible(cell: Cell) : RuleCell(cell) {
    val level = LEVEL

    override fun description() = """
        for a cell X without a defined number and with only one possibility I, define the number as I and clear it as a possibility
    """.trimIndent()

    fun description(cell: Cell) = """
        Single Possible
        for cell ${cell.position} without a defined number and with only one possibility ${cell.number}, define the number as ${cell.number}        
    """.trimIndent()

    override fun run(): RuleResult {
        if (cell.number != null) return RuleResult(false)
        val possibles = cell.possible
        if (possibles.size != 1) return RuleResult(false)
        val number = possibles.first()
        cell.setNumber(number)
        cell.removePossible(number)
        return RuleResult(true, description(cell), listOf(cell))
    }

    companion object {
        const val LEVEL = 2
    }
}