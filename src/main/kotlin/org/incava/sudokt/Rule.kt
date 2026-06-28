package org.incava.sudokt

import org.incava.io.Qlog

abstract class Rule {
    var updated = false

    abstract fun description() : String
    abstract fun run() : List<Cell>

    fun execute() : List<Cell> {
        updated = false
        return run()
    }

    fun setUpdated(cell: Cell) {
        updated = true
        Qlog.info("updating cell", cell)
        Qlog.info("description", description())
    }

    fun setUpdated(cell: Cell, description: String) {
        updated = true
        Qlog.info("updating cell", cell)
        Qlog.info("description", description)
    }
}