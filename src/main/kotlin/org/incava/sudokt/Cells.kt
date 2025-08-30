package org.incava.sudokt

class Cells(val cells: List<Cell>) {
    fun at(row: Int, column: Int) = cells[id(row, column)]
    fun at(position: Position) = cells[position.id]
    fun at(id: Int) = cells[id]
    fun id(row: Int, column: Int) = row * 9 + column
    fun ids() = cells.size
}