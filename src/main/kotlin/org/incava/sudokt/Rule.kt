package org.incava.sudokt

abstract class Rule(val cells: PuzzleCells) {
    var updated = false

    abstract fun description() : String
    abstract fun run() : List<Cell>

    fun execute() : List<Cell> {
        updated = false
        return run()
    }
}