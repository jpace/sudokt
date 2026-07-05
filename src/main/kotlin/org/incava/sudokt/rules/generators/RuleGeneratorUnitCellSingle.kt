package org.incava.sudokt.rules.generators

import org.incava.sudokt.Cell
import org.incava.sudokt.PuzzleCells
import org.incava.sudokt.rules.RuleCellUnit
import kotlin.random.Random

class RuleGeneratorUnitCellSingle {
    fun generateCellPair(
        puzzleCells: PuzzleCells,
        cls: Class<out RuleCellUnit>,
        unitType: String,
        unitFunction: (PuzzleCells, Int) -> List<Cell>
    ): RuleCellUnit {
        // two cells in the same unit
        val index = Random.nextInt(9)
        val unitCells = unitFunction(puzzleCells, index)
        val cell = unitCells.random()
        val ctor = cls.getConstructor(Cell::class.java, List::class.java, String::class.java)
        val instance = ctor.newInstance(cell, unitCells, unitType) as RuleCellUnit
        return instance
    }

    fun generate(puzzleCells: PuzzleCells, cls: Class<out RuleCellUnit>): RuleCellUnit {
        val unitFunction = UnitFunctions().getRandom()
        return generateCellPair(puzzleCells, cls, unitFunction.first, unitFunction.second)
    }
}