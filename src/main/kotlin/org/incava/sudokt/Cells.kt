package org.incava.sudokt

class Cells(val cells: List<Cell>) {
    val unitSize = 9
    val size: Int = cells.size

    fun at(row: Int, column: Int) = cells.first { it.position == positionOf(row, column) }
    fun at(id: Int) = cells[id]
    operator fun get(row: Int, column: Int) = cells.first { it.position == positionOf(row, column) }
    fun inRow(row: Int): List<Cell> {
        return filter { it.position.row == row }
    }

    fun inColumn(column: Int): List<Cell> {
        return filter { it.position.column == column }
    }

    fun inBox(box: Int): List<Cell> {
        return filter { it.position.box == box }
    }

    fun filter(block: (Cell) -> Boolean): List<Cell> {
        return cells.filter(block)
    }
}