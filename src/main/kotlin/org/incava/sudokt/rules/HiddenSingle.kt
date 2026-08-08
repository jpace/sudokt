package org.incava.sudokt.rules

import org.incava.io.Qlog
import org.incava.sudokt.Cell
import org.incava.sudokt.Rule
import org.incava.sudokt.RuleResult
import java.util.Locale

class HiddenSingle(val unitCells: List<Cell>, val unitType: String, val unitValue: Int) : Rule() {
    override fun description() = """
        in a unit with a number found as a possible in only one cell X, set the number of X to that value"
    """.trimIndent()

    fun description(cell: Cell) = """
        Single Possible in Unit $unitType
        in unit $unitType $unitValue with number ${cell.number} appearing only once in the $unitType,
        set that number in the cell ${cell.position}
    """.trimIndent()

    override fun run(): RuleResult {
        val updatedCells = mutableListOf<Cell>()
        (0 until 9).forEach { number ->
            val inCells = unitCells.filter { it.possible.values.contains(number) }
            if (inCells.size == 1) {
                val cell = inCells.first()
                cell.setNumber(number)
                updatedCells += cell
                Qlog.info("**************")
                Qlog.info("cell", cell)
                Qlog.info("number", number)
                return RuleResult(true, description(cell), listOf(cell))
            }
        }
        return RuleResult(false)
    }
}