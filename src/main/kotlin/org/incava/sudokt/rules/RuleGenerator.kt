package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.PuzzleCells
import org.incava.sudokt.Rule
import org.incava.sudokt.unit.CellsUnit
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

    fun generateCellPair(puzzleCells: PuzzleCells, cls: Class<out PairRule>): PairRule {
        val cellsUnit = puzzleCells.randomUnit()
        val x = cellsUnit.cells.random()
        val y = (cellsUnit.cells - x).random()
        val ctor = cls.getConstructor(Cell::class.java, Cell::class.java, CellsUnit::class.java)
        return ctor.newInstance(x, y, cellsUnit) as PairRule
    }

    fun generateCellUnit(puzzleCells: PuzzleCells, cls: Class<out RuleCellUnit>): RuleCellUnit {
        val cellsUnit = puzzleCells.randomUnit()
        val cell = cellsUnit.cells.random()
        val ctor = cls.getConstructor(Cell::class.java, CellsUnit::class.java)
        return ctor.newInstance(cell, cellsUnit) as RuleCellUnit
    }

    fun generateHiddenSingle(puzzleCells: PuzzleCells, cls: Class<out HiddenSingle>): HiddenSingle {
        val cellsUnit = puzzleCells.randomUnit()
        val ctor = cls.getConstructor(CellsUnit::class.java)
        return ctor.newInstance(cellsUnit) as HiddenSingle
    }
}