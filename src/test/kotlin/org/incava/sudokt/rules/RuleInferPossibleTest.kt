package org.incava.sudokt.rules

import org.junit.jupiter.api.assertAll
import kotlin.test.Test

class RuleInferPossibleTest : RuleTestBase() {
    @Test
    fun run() {
        assertAll(
            { assertPossible(none, 0 to 11) },
            { assertPossible(none, 0 to 1) }
        )
        val obj = RuleInferPossible(cells)
        obj.checkCell(0 to 0)
        obj.checkCell(0 to 1)
        assertAll(
            { assertPossible(none, 0 to 0) },
            { assertPossible(all, 0 to 1) }
        )
    }
}