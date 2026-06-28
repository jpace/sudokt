package org.incava.sudokt.rules

import org.incava.sudokt.PuzzleCells
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class RulesTest {
    @Test
    fun getByLevel() {
        val obj = Rules()
        val cells = PuzzleCells(emptyList())
        val result = obj.byLevel.map { it(cells).javaClass }
        val expected = listOf(
            RuleRemoveNumberFromPossiblesInUnit::class.java,
            RuleNakedPairs::class.java,
            RuleHiddenPairs::class.java
        )
        assertEquals(expected, result)
    }
}