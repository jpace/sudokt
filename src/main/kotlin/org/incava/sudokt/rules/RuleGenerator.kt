package org.incava.sudokt.rules

import org.incava.sudokt.PuzzleCells
import org.incava.sudokt.Rule
import org.incava.sudokt.rules.generators.RuleGeneratorCellPair
import org.incava.sudokt.rules.generators.RuleGeneratorSingleCell
import org.incava.sudokt.rules.generators.RuleGeneratorUnitCellSingle

class RuleGenerator {
    val rules = listOf(
        RuleCellInferPossible::class.java,
        RuleCellNakedSingle::class.java,
        RuleCellRemoveNumberFromUnitPossibles::class.java,
        RuleNakedPair::class.java,
        RuleHiddenPair::class.java,
    )

    fun generate(level: Int, puzzleCells: PuzzleCells): Rule? {
        val rule = rules[level]
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

    inline fun <reified T : Rule> asRule(cls: Class<out Rule>): Class<out T>? {
        return if (T::class.java.isAssignableFrom(cls)) {
            cls.asSubclass(T::class.java)
        } else {
            null
        }
    }
}