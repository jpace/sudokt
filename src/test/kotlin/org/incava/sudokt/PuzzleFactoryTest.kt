package org.incava.sudokt

import org.incava.sudokt.test.TestFixture
import kotlin.test.Test

class PuzzleFactoryTest {
    @Test
    fun fromString() {
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
        val obj = PuzzleFactory.createFromString(string)
        TestFixture.checkPuzzle(obj)
    }

    @Test
    fun fromString2() {
        val string = """
            ~   - 8 - 1 ~   -   - 6 ~ 7 -   - 5 ~
            ~ 5 - 6 - 3 ~ 8 -   -   ~   - 2 -   ~
            ~   -   -   ~ 3 - 9 - 5 ~ 8 -   -   ~
            
            ~   -   - 5 ~   - 6 - 7 ~ 3 - 1 -   ~
            ~   - 9 - 8 ~   -   -   ~ 6 -   -   ~
            ~   -   -   ~ 5 - 1 - 8 ~   -   -   ~

            ~ 8 -   - 6 ~   - 2 -   ~ 5 - 9 - 3 ~
            ~ 3 -   -   ~ 6 - 8 -   ~   -   - 2 ~
            ~ 2 - 7 -   ~ 1 -   -   ~   -   -   ~
        """.trimIndent()
        val obj = PuzzleFactory.createFromString(string)
        TestFixture.checkPuzzle(obj)
    }
}
