package org.incava.sudokt.rules

import org.incava.sudokt.Cells
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class RulesTest {
    @Test
    fun getByLevel() {
        val obj = Rules()
        val cells = Cells(emptyList())
        val result = obj.byLevel.map { it(cells).javaClass }
        val expected = listOf(
            RuleInferPossible::class.java,
            RuleSinglePossible::class.java,
            RuleRemoveNumberFromPossiblesInUnit::class.java,
            RuleNakedPairs::class.java,
            RuleHiddenPairs::class.java
        )
        assertEquals(expected, result)
    }
}