package org.incava.sudokt.rules.generators

import org.incava.io.Qlog
import org.incava.sudokt.Cell
import org.incava.sudokt.PuzzleCells
import org.incava.sudokt.Rule
import org.incava.sudokt.rules.RuleCell
import org.incava.sudokt.rules.RuleCellInferPossible
import org.incava.sudokt.rules.RuleCellPair
import org.incava.sudokt.rules.RuleHiddenPair
import org.incava.sudokt.rules.generators.RuleGeneratorSingleCell
import kotlin.random.Random

class RuleGeneratorCellPair {
    fun takeTwo(cells: List<Cell>): Pair<Cell, Cell> {
        val x = cells.random()
        val y = (cells - x).random()
        return Pair(x, y)
    }

    fun generateCellPair(
        puzzleCells: PuzzleCells,
        cls: Class<out RuleCellPair>,
        unitFunction: (PuzzleCells, Int) -> List<Cell>
    ): Rule {
        // two cells in the same unit
        val index = Random.nextInt(9)
        val unitCells = unitFunction(puzzleCells, index)
        val (x, y) = takeTwo(unitCells)
        Qlog.info("x", x)
        Qlog.info("y", y)
        val ctor = cls.getConstructor(Cell::class.java, Cell::class.java, List::class.java)
        val instance = ctor.newInstance(x, y, unitCells) as Rule
        Qlog.info("instance", instance)
        return instance
    }

    fun generate(puzzleCells: PuzzleCells, cls: Class<out RuleCellPair>): Rule {
        val unitFunction1: (PuzzleCells, Int) -> List<Cell> = PuzzleCells::inRow
        val unitFunction2: (PuzzleCells, Int) -> List<Cell> = PuzzleCells::inColumn
        val unitFunction3: (PuzzleCells, Int) -> List<Cell> = PuzzleCells::inBox
        val unitFunctions = listOf(unitFunction1, unitFunction2, unitFunction3)
        val unitFunction = unitFunctions.random()
        return generateCellPair(puzzleCells, cls, unitFunction)
    }
}