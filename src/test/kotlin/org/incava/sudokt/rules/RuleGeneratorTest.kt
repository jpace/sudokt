package org.incava.sudokt.rules

import org.incava.io.Qlog
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
        Qlog.info("puzzle", puzzle)
        puzzleCells = PuzzleCells(puzzle.cells)
    }

    @Test
    fun generateLevel0() {
        val obj = RuleGenerator()
        val result = obj.generate(0, puzzleCells)
        Qlog.info("result", result)
        assertNotNull(result)
        assertIs<RuleCellInferPossible>(result)
    }

    @Test
    fun generateLevel1() {
        val obj = RuleGenerator()
        val result = obj.generate(1, puzzleCells)
        Qlog.info("result", result)
        assertNotNull(result)
        assertIs<RuleHiddenPair>(result)
    }
}