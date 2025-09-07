package org.incava.sudokt

class Cells(private val elements: List<Cell>) {
    val size: Int = elements.size

    operator fun get(row: Int, column: Int) = elements.first { it.position == positionOf(row, column) }

    fun inRow(row: Int): List<Cell> {
        return filterForPosition(row) { it.row }
    }

    fun inColumn(column: Int): List<Cell> {
        return filterForPosition(column) { it.column }
    }

    fun inBox(box: Int): List<Cell> {
        return filterForPosition(box) { it.box }
    }

    fun filterForPosition(value: Int, block: (Position) -> Int): List<Cell> {
        return elements.filter { value == block(it.position) }
    }

    fun filter(block: (Cell) -> Boolean): List<Cell> {
        return elements.filter { block(it) }
    }
}