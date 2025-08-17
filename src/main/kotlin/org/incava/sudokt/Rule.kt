package org.incava.sudokt

abstract class Rule(val cells: List<Cell>) {
    var updated = false

    abstract fun run()
}