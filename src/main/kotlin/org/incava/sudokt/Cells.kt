package org.incava.sudokt

class Cells(val cells: List<Cell>) {
    val size: Int = cells.size

    operator fun get(row: Int, column: Int) = cells.first { it.position == positionOf(row, column) }

    fun inRow(row: Int): List<Cell> {
        return filter(row) { it.row }
    }

    fun inColumn(column: Int): List<Cell> {
        return filter(column) { it.column }
    }

    fun inBox(box: Int): List<Cell> {
        return filter(box) { it.box }
    }

    fun filter(value: Int, block: (Position) -> Int): List<Cell> {
        return cells.filter { value == block(it.position) }
    }
}