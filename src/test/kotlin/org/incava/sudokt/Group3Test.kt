package org.incava.sudokt

import org.junit.jupiter.api.assertAll
import kotlin.test.Test
import kotlin.test.assertEquals

class GroupTest {
    @Test
    fun cells() {
        val obj = Group()
        val result = obj.cells
        assertAll(
            { assertEquals(9, result.size) }
        )
    }
}