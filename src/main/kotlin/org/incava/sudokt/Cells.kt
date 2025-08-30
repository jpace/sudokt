package org.incava.sudokt

class Cells(val cells: List<Cell>) {
    val unitSize = 9
    val size: Int = cells.size

    fun at(row: Int, column: Int) = cells.first { it.position == positionOf(row, column) }
    fun at(id: Int) = cells[id]
    fun inRow(row: Int): List<Cell> {
        return filterCells(row) { it.row }
    }
    fun inColumn(column: Int): List<Cell> {
        return filterCells(column) { it.column }
    }
    fun inBox(box: Int): List<Cell> {
        return filterCells(box) { it.box }
    }
    fun filterCells(value: Int, accessor: (Position) -> Int): List<Cell> {
        return cells.filter { accessor(it.position) == value }
    }
}