package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.Rule
import org.incava.sudokt.RuleResult
import org.incava.sudokt.impl.PuzzleData

class HiddenSingle(val unitCells: List<Cell>, val unitType: String, val unitValue: Int) : Rule() {
    override fun description() = """
        in a unit with a number N found as a possible in only one cell X, set the number of X to N"
    """.trimIndent()

    fun description(cell: Cell) = """
        Hidden Single in Unit $unitType
        in unit $unitType $unitValue with number ${cell.number} appearing only once in the $unitType,
        set that number in the cell ${cell.position}
    """.trimIndent()

    override fun run(): RuleResult {
        PuzzleData.numbers.forEach { number ->
            val inCells = unitCells.filter { it.possible.values.contains(number) }
            if (inCells.size == 1) {
                val cell = inCells.first()
                cell.setNumber(number)
                return RuleResult(true, description(cell), listOf(cell))
            }
        }
        return RuleResult(false)
    }
}