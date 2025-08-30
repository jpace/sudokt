package org.incava.sudokt

class Puzzle {
    val cells: List<Cell> = (0 until 81).map { Cell(it) }

    fun setCells(row: Int, numbers: List<Int?>) {
        numbers.indices.forEach {
            if (numbers[it] != null) {
                setCell(row, it, numbers[it]!!)
            }
        }
    }

    fun setCell(row: Int, column: Int, number: Int) {
        val position = Util.rowColumnToPosition(row, column)
        setCell(position, number)
    }

    fun setCell(id: Int, number: Int) {
        cells[id].setNumber(number)
    }

    fun setCell(position: Position, number: Int) {
        cells[position.id].setNumber(number)
    }

    fun isSolved() : Boolean {
        return cells.all { it.number != null }
    }

    fun setCells(row: Int, columns: Map<Int, Int>) {
        columns.forEach { (column, value) -> setCell(row, column, value) }
    }
}
