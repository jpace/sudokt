package org.incava.sudokt

class Position(val id: Int) {
    constructor(row: Int, column: Int) : this(row * 9 + column)

    val row = id / 9
    val column = id % 9
    val box = column / 3 + row / 3 * 3

}