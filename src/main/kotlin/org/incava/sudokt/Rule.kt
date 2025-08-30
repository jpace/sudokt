package org.incava.sudokt

abstract class Rule(open val cells: Cells) {
    var updated = false

    abstract fun run()

    fun execute() {
        updated = false
        run()
    }
}