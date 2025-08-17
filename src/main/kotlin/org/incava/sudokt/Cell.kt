package org.incava.sudokt

class Cell(val id: Int) {
    val possible: MutableSet<Int> = (1..9).toMutableSet()

    fun removePossible(num: Int) {
        possible.remove(num)
    }

    fun number(): Int? {
        return if (possible.size == 1) possible.first() else null
    }

    fun setNumber(num: Int) {
        possible.clear()
        possible += num
    }

    fun row() = id / 9

    fun column() = id % 9

    fun box(): Int {
        val x = column() / 3
        val y = row() / 3
        return x + y * 3
    }
}