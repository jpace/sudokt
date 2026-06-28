package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.PuzzleCells
import org.incava.sudokt.impl.PuzzleData

class RuleInferPossible(cells: PuzzleCells) : RuleEachCell(cells) {
    override fun description() = """
        for a cell X without a defined number, set the possible values to 1 through 9"
    """.trimIndent()

    override fun checkCell(cell: Cell): Boolean {
        return if (cell.number == null) {
            updated = cell.setPossibles(PuzzleData.numbers) || updated
            true
        } else {
            false
        }
    }
}