package org.incava.sudokt.rules

import org.incava.sudokt.PuzzleCells
import org.incava.sudokt.PuzzleFactory
import org.junit.jupiter.api.Test
import kotlin.test.assertIs
import kotlin.test.assertNotNull

class RuleGeneratorTest {
    val puzzleCells: PuzzleCells

    init {
        val string = """
            ||   | 8 | 1 ||   |   | 6 || 7 |   | 5 ||
            || 5 | 6 | 3 || 8 |   |   ||   | 2 |   ||
            ||   |   |   || 3 | 9 | 5 || 8 |   |   ||
            
            ||   |   | 5 ||   | 6 | 7 || 3 | 1 |   ||
            ||   | 9 | 8 ||   |   |   || 6 |   |   ||
            ||   |   |   || 5 | 1 | 8 ||   |   |   ||

            || 8 |   | 6 ||   | 2 |   || 5 | 9 | 3 ||
            || 3 |   |   || 6 | 8 |   ||   |   | 2 ||
            || 2 | 7 |   || 1 |   |   ||   |   |   ||
        """.trimIndent()
        val puzzle = PuzzleFactory.createFromString(string)
        puzzleCells = PuzzleCells(puzzle.cells)
    }

    inline fun <reified T> runIt(level: Int) {
        val obj = RuleGenerator()
        val result = obj.generate(level, puzzleCells)
        assertNotNull(result)
        assertIs<T>(result)
    }

    @Test
    fun generateLevel0() = runIt<RuleCellInferPossible>(0)

    @Test
    fun generateLevel1() = runIt<RuleCellSinglePossible>(1)

    @Test
    fun generateLevel2() = runIt<RuleCellRemoveNumberFromUnitPossibles>(2)

    @Test
    fun generateLevel3() = runIt<RuleHiddenPair>(3)
}