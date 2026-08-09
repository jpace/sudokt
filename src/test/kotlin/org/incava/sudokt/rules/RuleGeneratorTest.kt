package org.incava.sudokt.rules

import org.incava.io.Qlog
import org.incava.sudokt.Puzzle
import org.incava.sudokt.PuzzleCells
import org.incava.sudokt.PuzzleFactory
import org.incava.sudokt.Rule
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNotNull

class RuleGeneratorTest {
    val puzzle: Puzzle
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
        puzzle = PuzzleFactory.createFromString(string)
        puzzleCells = PuzzleCells(puzzle.cells)
    }

    inline fun <reified T> runIt(level: Int) {
        val obj = RuleGenerator()
        val result = obj.generate(level, puzzleCells)
        assertNotNull(result)
        assertIs<T>(result)
    }

    @Test
    fun generateLevel0() = runIt<InferPossible>(0)

    @Test
    fun generateLevel0All() {
        val generator = RuleGenerator()
        val unprocessed = puzzle.cells.toMutableSet()
        while (unprocessed.isNotEmpty()) {
            val rule = generator.generate(0, puzzleCells) as InferPossible
            Qlog.info("rule", rule)
            val cell = rule.cell
            Qlog.info("cell", cell)
            unprocessed.remove(cell)
            if (cell.number != null) continue
            if (cell.possible.values.isNotEmpty()) continue
            val result = rule.run()
            Qlog.info("result", result)
            assertEquals(1, result.cells.size)
        }
    }

    @ParameterizedTest
    @MethodSource("generateLevelData")
    fun generateLevel(data: Pair<Int, Class<out Rule>>) {
        Qlog.info("data", data)
        val obj = RuleGenerator()
        val result = obj.generate(data.first, puzzleCells)
        assertNotNull(result)
        assertInstanceOf(data.second, result)
    }

    companion object {
        @JvmStatic
        fun generateLevelData() = listOf(
            InferPossible::class.java,
            PrunePeers::class.java,
            NakedSingle::class.java,
            HiddenSingle::class.java,
            NakedPair::class.java,
            HiddenPair::class.java
        ).mapIndexed { index, cls -> Pair(index, cls) }
    }

    @Test
    fun generateSingleCell() {
        val rule = InferPossible::class.java
        val obj = RuleGenerator()
        val result = obj.generateSingleCell(puzzleCells, rule)
        Qlog.info("result", result)
        assertNotNull(result)
        assertIs<InferPossible>(result)
    }

    @Test
    fun generateCellPair() {
        val rule = HiddenPair::class.java
        val obj = RuleGenerator()
        val result = obj.generateCellPair(puzzleCells, rule)
        Qlog.info("result", result)
        assertNotNull(result)
        assertIs<HiddenPair>(result)
    }
}