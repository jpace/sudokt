package org.incava.sudokt.rules.generators

import org.incava.sudokt.Cell
import org.incava.sudokt.PuzzleCells
import org.incava.sudokt.rules.RuleCellPair
import kotlin.random.Random

class RuleGeneratorCellPair {
    fun takeTwo(cells: List<Cell>): Pair<Cell, Cell> {
        // @todo to select two cells that match the rule precondition (e.g., both have numbers)
        val x = cells.random()
        val y = (cells - x).random()
        return Pair(x, y)
    }

    fun generateCellPair(puzzleCells: PuzzleCells, cls: Class<out RuleCellPair>): RuleCellPair {
        // two cells in the same unit
        val index = Random.nextInt(9)
        val unitFunction = UnitFunctions().getRandom()
        val unitType = unitFunction.first
        val unitCells = unitFunction.second(puzzleCells, index)
        val (x, y) = takeTwo(unitCells)
        val ctor = cls.getConstructor(Cell::class.java, Cell::class.java, List::class.java, String::class.java)
        val instance = ctor.newInstance(x, y, unitCells, unitType) as RuleCellPair
        return instance
    }

    fun generate(puzzleCells: PuzzleCells, cls: Class<out RuleCellPair>): RuleCellPair {
        return generateCellPair(puzzleCells, cls)
    }
}