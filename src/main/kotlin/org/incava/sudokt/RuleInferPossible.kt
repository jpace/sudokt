package org.incava.sudokt

class RuleInferPossible(cells: List<Cell>) : Rule(cells) {
    override fun run() {
        updated = true
        cells.forEach { cell ->
            if (cell.number == null) {
                val possible = (1..9).toMutableSet()
                cell.updatePossible(possible)
            }
        }
    }
}