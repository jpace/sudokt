package org.incava.sudokt.rules

import org.incava.sudokt.Cell

// level 1 rule
class RuleCellSinglePossible(cell: Cell) : RuleCell(cell) {
    val level = LEVEL

    override fun description() = """
        for a cell X without a defined number and with only one possibility I, define the number as I and clear it as a possibility"
    """.trimIndent()

    fun description(cell: Cell) = """
        for cell ${cell.position} without a defined number and with only one possibility ${cell.number}, define the number as ${cell.number}"        
    """.trimIndent()

    override fun run(): List<Cell> {
        if (cell.number != null) return emptyList()
        val possibles = cell.possible
        if (possibles.size != 1) return emptyList()
        val number = possibles.first()
        cell.setNumber(number)
        cell.removePossible(number)
        setUpdated(cell, description(cell))
        return listOf(cell)
    }

    companion object {
        const val LEVEL = 2
    }
}