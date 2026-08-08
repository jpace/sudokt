package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

class InferPossibleTest {
    @Test
    fun `no number set`() {
        val arg = Cell(1)
        val obj = InferPossible(arg)
        val result = obj.run()
        assertEquals(listOf(arg), result.cells)
        assertTrue(result.updated)
        assertSame(arg, result.cells.first())
        assertEquals(9, arg.possible.size)
        assertEquals(
            """
                Infer Possible
                for cell 1,2 without a defined number, set the possible values to 1 through 9
                """.trimIndent(), result.description
        )
    }

    @Test
    fun `number set`() {
        val arg = Cell(1).also { it.number = 3 }
        val obj = InferPossible(arg)
        val result = obj.run()
        assertFalse(result.updated)
        assertEquals(emptyList(), result.cells)
        assertTrue(arg.possible.isEmpty())
        assertEquals("", result.description)
    }
}