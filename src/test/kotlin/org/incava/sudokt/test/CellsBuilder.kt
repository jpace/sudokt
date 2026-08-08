package org.incava.sudokt.test

import org.incava.sudokt.Cell
import org.incava.sudokt.PuzzleCells
import org.incava.sudokt.impl.PuzzleData

class CellsBuilder {
    var defaultPossible: Set<Int> = PuzzleData.cellIndices.toSet()
    var possibles = mutableMapOf<Int, Set<Int>>()

    fun withDefaultPossible(newValues: Set<Int>): CellsBuilder {
        defaultPossible = newValues
        return this
    }

    fun withPossible(id: Int, newValues: Set<Int>): CellsBuilder {
        possibles[id] = newValues
        return this
    }

    fun removeDefaultPossible(values: Set<Int>): CellsBuilder {
        defaultPossible = defaultPossible - values
        return this
    }

    fun removeDefaultPossible(vararg values: Int): CellsBuilder {
        defaultPossible = defaultPossible - values.toSet()
        return this
    }

    fun newCell(id: Int, number: Int) = Cell(id).also { it.number = number }

    fun newCell(id: Int, possible: Set<Int>) = Cell(id).apply { setPossibles(possible) }

    fun newCells(): List<Cell> {
        return PuzzleData.cellIndices.map { id ->
            val possible = possibles[id] ?: defaultPossible
            newCell(id, possible)
        }.toList()
    }
}