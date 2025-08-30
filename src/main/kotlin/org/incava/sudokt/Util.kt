package org.incava.sudokt

object Util {
    fun rowColumnToId(row: Int, column: Int) = row * 9 + column
    fun rowColumnToPosition(row: Int, column: Int) = Position(row * 9 + column)
}