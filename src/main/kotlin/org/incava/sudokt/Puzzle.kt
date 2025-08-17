package org.incava.sudokt

class Puzzle {
    val cells: List<Cell> = (0 until 81).map { Cell(it) }

    fun rowColumnToId(row: Int, column: Int) = row * 9 + column

    fun setCells(row: Int, numbers: List<Int?>) {
        numbers.indices.forEach {
            if (numbers[it] != null) {
                setCell(row, it, numbers[it]!!)
            }
        }
    }

    fun setCell(row: Int, column: Int, number: Int) {
        val id = rowColumnToId(row, column)
        setCell(id, number)
    }

    fun setCell(id: Int, number: Int) {
        cells[id].setNumber(number)
    }

    fun isSolved() : Boolean {
        return cells.all { it.number != null }
    }
}
