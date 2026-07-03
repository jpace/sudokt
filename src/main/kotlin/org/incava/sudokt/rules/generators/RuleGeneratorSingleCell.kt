package org.incava.sudokt.rules.generators

import org.incava.sudokt.Cell
import org.incava.sudokt.PuzzleCells
import org.incava.sudokt.Rule
import org.incava.sudokt.rules.RuleCell
import kotlin.random.Random

class RuleGeneratorSingleCell {
    fun generate(puzzleCells: PuzzleCells, cls: Class<out RuleCell>): Rule {
        val row = Random.nextInt(9)
        val column = Random.nextInt(9)
        val cell = puzzleCells[row, column]
        val ctor = cls.getConstructor(Cell::class.java)
        return ctor.newInstance(cell) as Rule
    }
}