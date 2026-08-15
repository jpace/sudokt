package org.incava.sudokt

import org.incava.sudokt.unit.CellsUnit
import kotlin.random.Random

class PuzzleCells(private val cells: List<Cell>) {
    val size: Int = cells.size
    val unitTypes: List<String> = listOf("row", "column", "box")

    operator fun get(row: Int, column: Int) = cells.first { it.position == positionOf(row, column) }

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
        return cells.filter { value == block(it.position) }
    }

    fun randomUnit(): CellsUnit {
        val index = Random.nextInt(9)
        val type = unitTypes.random()
        val filter = when (type) {
            "row" -> Position::row
            "column" -> Position::column
            "box" -> Position::box
            else -> throw IllegalArgumentException("Unknown type: $type")
        }
        val unitCells = cells.filter { filter(it.position) == index }
        return CellsUnit(unitCells, index + 1, type)
    }
}