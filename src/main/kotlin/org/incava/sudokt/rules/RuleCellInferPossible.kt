package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.RuleResult
import org.incava.sudokt.impl.PuzzleData

// level 1 rule
class RuleCellInferPossible(cell: Cell) : RuleCell(cell) {
    val level = LEVEL

    override fun description() = """
        for a cell X without a defined number, set the possible values to 1 through 9
    """.trimIndent()

    fun description(cell: Cell) = """
        Infer Possible
        for cell ${cell.position} without a defined number, set the possible values to 1 through 9
    """.trimIndent()

    override fun run(): RuleResult {
        return if (cell.number == null && cell.setPossibles(PuzzleData.numbers)) {
            RuleResult(true, description(cell), listOf(cell))
        } else {
            RuleResult(false)
        }
    }

    companion object {
        const val LEVEL = 1
    }
}