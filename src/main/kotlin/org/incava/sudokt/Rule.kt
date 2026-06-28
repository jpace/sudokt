package org.incava.sudokt

abstract class Rule {
    var updated = false

    abstract fun description() : String
    abstract fun run() : List<Cell>

    fun execute() : List<Cell> {
        updated = false
        return run()
    }
}