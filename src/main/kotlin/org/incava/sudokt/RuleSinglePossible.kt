package org.incava.sudokt

class RuleSinglePossible(cells: List<Cell>) : Rule(cells) {
    override fun run() {
        updated = false
        cells
            .filter { it.number == null && it.possible.size == 1 }
            .forEach {
                val number = it.possible.first()
                it.setNumber(number)
                it.removePossible(number)
                updated = true
            }
    }
}