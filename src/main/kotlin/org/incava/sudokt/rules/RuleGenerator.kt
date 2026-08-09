package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.PuzzleCells
import org.incava.sudokt.Rule
import org.incava.sudokt.rules.generators.UnitFunctions
import kotlin.random.Random

class RuleGenerator {
    val rules = listOf(
        InferPossible::class.java,
        PrunePeers::class.java,
        NakedSingle::class.java,
        HiddenSingle::class.java,
        NakedPair::class.java,
        HiddenPair::class.java,
    )

    fun generate(level: Int, puzzleCells: PuzzleCells): Rule? {
        val rule = rules[level]
        asRule<RuleCell>(rule)?.let {
            return generateSingleCell(puzzleCells, it)
        }
        asRule<PairRule>(rule)?.let {
            return generateCellPair(puzzleCells, it)
        }
        asRule<RuleCellUnit>(rule)?.let {
            return generateCellUnit(puzzleCells, it)
        }
        asRule<HiddenSingle>(rule)?.let {
            return generateHiddenSingle(puzzleCells, it)
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

    fun generateSingleCell(puzzleCells: PuzzleCells, cls: Class<out RuleCell>): RuleCell {
        val row = Random.nextInt(9)
        val column = Random.nextInt(9)
        val cell = puzzleCells[row, column]
        val ctor = cls.getConstructor(Cell::class.java)
        return ctor.newInstance(cell) as RuleCell
    }

    fun getUnitFunction() = UnitFunctions().getRandom()

    fun generateCellPair(puzzleCells: PuzzleCells, cls: Class<out PairRule>): PairRule {
        // two cells in the same unit
        val index = Random.nextInt(9)
        val (unitType, function) = getUnitFunction()
        val unitCells = function(puzzleCells, index)
        val x = unitCells.random()
        val y = (unitCells - x).random()
        val ctor = cls.getConstructor(Cell::class.java, Cell::class.java, List::class.java, String::class.java)
        return ctor.newInstance(x, y, unitCells, unitType) as PairRule
    }

    fun generateCellUnit(puzzleCells: PuzzleCells, cls: Class<out RuleCellUnit>): RuleCellUnit {
        val (unitType, function) = getUnitFunction()
        // two cells in the same unit
        val index = Random.nextInt(9)
        val unitCells = function(puzzleCells, index)
        val cell = unitCells.random()
        val ctor = cls.getConstructor(Cell::class.java, List::class.java, String::class.java)
        return ctor.newInstance(cell, unitCells, unitType) as RuleCellUnit
    }

    fun generateHiddenSingle(puzzleCells: PuzzleCells, cls: Class<out HiddenSingle>): HiddenSingle {
        val (unitType, function) = getUnitFunction()
        val index = Random.nextInt(9)
        val unitCells = function(puzzleCells, index)
        val ctor = cls.getConstructor(List::class.java, String::class.java, Int::class.java)
        return ctor.newInstance(unitCells, unitType, index + 1)
    }
}