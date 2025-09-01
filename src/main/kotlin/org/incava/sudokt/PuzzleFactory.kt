package org.incava.sudokt

object PuzzleFactory {
    private val linesRegexp = Regex("\r?\n")
    private val cellsRegexp1 = Regex("\\s*\\|+\\s*")
    private val cellsRegexp2 = Regex("\\s*[~\\-]+\\s*")

    fun createFromString(string: String): Puzzle {
        val puzzle = Puzzle()
        var row = 0
        string.split(linesRegexp)
            .filter { it.isNotEmpty() }
            .forEach {
                val x = getCells(it, cellsRegexp1)
                val cells = x.ifEmpty { getCells(it, cellsRegexp2) }
                if (cells.isNotEmpty()) {
                    setRow(puzzle, row, cells)
                    row++
                }
            }
        return puzzle
    }

    fun getCells(line: String, regex: Regex): List<String> {
        val cells = line.split(regex)
        return if (cells.size == 11) {
            cells.subList(1, cells.size - 1)
        } else {
            emptyList()
        }
    }

    fun setRow(puzzle: Puzzle, row: Int, values: List<String>) {
        values.indices.forEach { index ->
            val value = values[index]
            if (value.isNotEmpty()) {
                val number = value.toInt()
                puzzle.setCell(row, index, number)
            }
        }
    }
}