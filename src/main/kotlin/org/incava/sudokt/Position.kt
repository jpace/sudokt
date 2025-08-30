package org.incava.sudokt

fun positionOf(id: Int) = PositionFactory.create(id)
fun positionOf(row: Int, column: Int) = PositionFactory.create(row, column)

data class Position(val id: Int) {
    val row = id / 9
    val column = id % 9
    val box = column / 3 + row / 3 * 3
}

object PositionFactory {
    fun create(id: Int) = Position(id)
    fun create(row: Int, column: Int) = Position(Util.rowColumnToId(row, column))
}