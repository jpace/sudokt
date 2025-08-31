package org.incava.sudokt.rules

import org.junit.jupiter.api.assertAll
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class RuleInferPossibleTest : RuleTestBase() {
    @Test
    fun run() {
        assertAll(
            { assertNull(cells[0, 0].number) },
            { assertPossible(none, 0 to 0) },
            { assertEquals(8, cells[0, 1].number) },
            { assertPossible(none, 0 to 1) }
        )
        val obj = RuleInferPossible(cells)
        obj.checkCell(cells[0, 0])
        obj.checkCell(cells[0, 1])
        assertAll(
            { assertPossible(all, 0 to 0) },
            { assertPossible(none, 0 to 1) }
        )
    }
}