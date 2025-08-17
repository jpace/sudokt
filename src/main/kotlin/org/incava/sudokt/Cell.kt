package org.incava.sudokt

class Cell {
    val possible: MutableSet<Int> = (1 .. 9).toMutableSet()

    fun removePossible(num: Int) {
        possible.remove(num)
    }

    fun number() : Int? {
        return if (possible.size == 1) possible.first() else null
    }

    fun setNumber(num: Int) {
        possible.clear()
        possible += num
    }
}