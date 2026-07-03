package org.incava.sudokt.rules

import org.incava.io.Qlog
import org.incava.sudokt.PuzzleCells
import org.incava.sudokt.Rule
import org.incava.sudokt.rules.generators.RuleGeneratorCellPair
import org.incava.sudokt.rules.generators.RuleGeneratorSingleCell
import org.incava.sudokt.rules.generators.RuleGeneratorUnitCellSingle

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
        asRule<RuleCell>(rule)?.let {
            val generator = RuleGeneratorSingleCell()
            return generator.generate(puzzleCells, it)
        }
        asRule<RuleCellPair>(rule)?.let {
            val generator = RuleGeneratorCellPair()
            return generator.generate(puzzleCells, it)
        }
        asRule<RuleCellUnit>(rule)?.let {
            val generator = RuleGeneratorUnitCellSingle()
            return generator.generate(puzzleCells, it)
        }
        throw IllegalArgumentException("Unknown rule type: $rule")
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
    fun asRuleUnitCellSingle(cls: Class<out Rule>): Class<out RuleCellUnit>? {
        return if (RuleCellUnit::class.java.isAssignableFrom(cls)) {
            cls.asSubclass(RuleCellUnit::class.java)
        } else {
            null
        }
    }

    inline fun <reified T : Rule> asRule(cls: Class<out Rule>): Class<out T>? {
        return if (T::class.java.isAssignableFrom(cls)) {
            cls.asSubclass(T::class.java)
        } else {
            null
        }
    }
}