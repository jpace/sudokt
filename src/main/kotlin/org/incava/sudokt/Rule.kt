package org.incava.sudokt

abstract class Rule(open val cells: Cells) {
    var updated = false

    abstract fun run() : List<Cell>

    fun execute() : List<Cell> {
        updated = false
        return run()
    }
}