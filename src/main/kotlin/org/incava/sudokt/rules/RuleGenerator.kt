package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.Rule

data class RuleGeneratorState(val matched: MutableMap<Cell, Rule>, val notMatched: MutableMap<Cell, Rule>)

class RuleGenerator {
    fun generate() {
    }

    fun setMatched(cell: Cell, rule: Class<Rule>) {
    }

    fun setNotMatched(cell: Cell, rule: Class<Rule>) {
    }
}