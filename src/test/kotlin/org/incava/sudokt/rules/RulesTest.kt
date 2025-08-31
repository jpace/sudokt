package org.incava.sudokt.rules

import org.incava.sudokt.Cells
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertAll
import kotlin.test.Test

class RulesTest {
    @Test
    fun getByLevel() {
        val obj = Rules()
        val cells = Cells(emptyList())
        assertAll(
            { assertEquals(RuleInferPossible::class.java, obj.byLevel[0](cells).javaClass) },
            { assertEquals(RuleSinglePossible::class.java, obj.byLevel[1](cells).javaClass) },
            { assertEquals(RuleApplyNumberToGroups::class.java, obj.byLevel[2](cells).javaClass) },
            { assertEquals(RuleTwoPairs::class.java, obj.byLevel[3](cells).javaClass) },
        )
    }
}