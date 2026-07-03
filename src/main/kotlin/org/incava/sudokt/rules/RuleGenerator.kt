package org.incava.sudokt.rules

import org.incava.io.Qlog
import org.incava.sudokt.PuzzleCells
import org.incava.sudokt.Rule
import org.incava.sudokt.rules.generators.RuleGeneratorCellPair
import org.incava.sudokt.rules.generators.RuleGeneratorSingleCell

class RuleGenerator {
    val rules = listOf(
        RuleCellInferPossible::class.java,
        RuleCellSinglePossible::class.java,
        RuleCellRemoveNumberFromUnitPossibles::class.java,
        RuleHiddenPair::class.java,
    )

    fun generate(level: Int, puzzleCells: PuzzleCells): Rule? {
        val rule = rules[level]
        Qlog.info("rule", rule.simpleName)
        asSingleCellRule(rule)?.let {
            val generator = RuleGeneratorSingleCell()
            return generator.generate(puzzleCells, it)
        }
        asPairRule(rule)?.let {
            val generator = RuleGeneratorCellPair()
            return generator.generate(puzzleCells, it)
        }
        return null
    }

    fun asSingleCellRule(cls: Class<out Rule>): Class<out RuleCell>? {
        return if (RuleCell::class.java.isAssignableFrom(cls)) {
            cls.asSubclass(RuleCell::class.java)
        } else {
            null
        }
    }

    fun asPairRule(cls: Class<out Rule>): Class<out RuleCellPair>? {
        return if (RuleCellPair::class.java.isAssignableFrom(cls)) {
            cls.asSubclass(RuleCellPair::class.java)
        } else {
            null
        }
    }
}