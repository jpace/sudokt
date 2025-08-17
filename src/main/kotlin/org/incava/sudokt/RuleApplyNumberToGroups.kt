package org.incava.sudokt

class RuleApplyNumberToGroups(cells: List<Cell>) : Rule(cells) {
    private fun checkMatch(x: Cell, index: Int, supplier: (Cell) -> Int) {
        val other = cells[index]
        if (supplier(x) == supplier(other) && other.possible.contains(x.number)) {
            other.removePossible(x.number!!)
            updated = true
        }
    }

    override fun run() {
        updated = false
        cells.indices.forEach { index ->
            val cell = cells[index]
            cells.indices
                .filter { cell.number != null }
                .filter { it != index }
                .forEach { other ->
                    checkMatch(cell, other) { it.row() }
                    checkMatch(cell, other) { it.column() }
                    checkMatch(cell, other) { it.box() }
                }
        }
    }
}